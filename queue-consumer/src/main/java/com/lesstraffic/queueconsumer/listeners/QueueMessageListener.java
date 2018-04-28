package com.lesstraffic.queueconsumer.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesstraffic.queueconsumer.entities.Action;
import com.lesstraffic.queueconsumer.repositories.EventRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
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
import java.util.Objects;

import static java.util.Objects.nonNull;

@Component
public class QueueMessageListener implements MessageListener<String, String> {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestTemplate restTemplate;

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
            StrSubstitutor strSubstitutor = new StrSubstitutor(bagResponse);

            eventRepository.findByTopic(topic)
                .ifPresent(event -> {
                    List<Action> actions = event.getActions();
                    actions.forEach(action -> {
                        String endpoint = getUrl(action.getEndpoint());
                        String finalBody = action.getTemplate();

                        HttpEntity<String> entity = new HttpEntity<>(strSubstitutor.replace(finalBody), headers);
                        Map<String, Object> response = null;

                        switch(action.getMethod()){
                            case GET:
                                response = restTemplate.getForObject(endpoint, HashMap.class);
                                break;
                            case POST:
                                response = restTemplate.postForObject(endpoint, entity, HashMap.class);
                               break;
                            case PUT:
                                restTemplate.put(endpoint, entity);
                                break;
                            case PATCH:
                                response = restTemplate.patchForObject(endpoint, entity, HashMap.class);
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