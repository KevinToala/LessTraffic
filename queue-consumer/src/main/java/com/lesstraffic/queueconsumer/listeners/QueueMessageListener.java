package com.lesstraffic.queueconsumer.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesstraffic.queueconsumer.entities.Action;
import com.lesstraffic.queueconsumer.repositories.EventRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.apache.commons.text.StringSubstitutor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Component
public class QueueMessageListener implements MessageListener<String, String> {
    @Autowired private EventRepository eventRepository;
    @Autowired private RestTemplate restTemplate;

    @Autowired @Qualifier("eurekaClient")
    private EurekaClient eurekaClient;

    @Override
    @SuppressWarnings("unchecked")
    public void onMessage(ConsumerRecord<String, String> record){
        String topic = record.topic();

        try {
            Map<String, Object> queueJson = new ObjectMapper().readValue(record.value(), HashMap.class);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> bagResponse = new HashMap<>(queueJson);
            StringSubstitutor strSubstitutor = new StringSubstitutor(bagResponse);

            eventRepository.findByTopic(topic)
                .ifPresent(event -> {
                    List<Action> actions = event.getActions();
                    actions.forEach(action -> {
                        String urlWithEndpoint = getUrl(action.getEndpoint());
                        String finalEndpoint = strSubstitutor.replace(urlWithEndpoint);
                        String finalBody = strSubstitutor.replace(action.getTemplate());

                        HttpEntity<String> entity = new HttpEntity<>(finalBody, headers);
                        Map<String, Object> response = null;

                        switch(action.getMethod()){
                            case GET:
                                response = restTemplate.getForObject(finalEndpoint, HashMap.class);
                                break;
                            case POST:
                                response = restTemplate.postForObject(finalEndpoint, entity, HashMap.class);
                               break;
                            case PUT:
                                restTemplate.put(finalEndpoint, entity);
                                break;
                            case PATCH:
                                response = restTemplate.patchForObject(finalEndpoint, entity, HashMap.class);
                                break;
                        }

                        if(nonNull(response)){
                            bagResponse.putAll(response);
                        }
                    });
                });
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(HttpClientErrorException | HttpServerErrorException e){
            System.err.println(e.getResponseBodyAsString());
        }
    }

    public String getUrl(String endpoint){
        Application application = eurekaClient.getApplication("gateway-server");
        InstanceInfo instanceInfo = application.getInstances().get(0);

        return String.format("http://%s:%d/%s", instanceInfo.getIPAddr(), instanceInfo.getPort(), endpoint);
    }
}