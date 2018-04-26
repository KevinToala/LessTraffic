package com.lesstraffic.queueconsumer.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesstraffic.queueconsumer.entities.Action;
import com.lesstraffic.queueconsumer.repositories.EventRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class QueueMessageListener implements MessageListener<String, String> {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Override
    @SuppressWarnings("unchecked")
    public void onMessage(ConsumerRecord<String, String> record){
        String topic = record.topic();
        HashMap<String, Object> queueJson;

        try {
            queueJson = new ObjectMapper().readValue(record.value(), HashMap.class);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        eventRepository.findByTopic(topic)
            .ifPresent(event -> {
                List<Action> actions = event.getActions();
                actions.forEach(action -> {
                    String endpoint = action.getEndpoint();
                    String finalBody = action.getTemplate();

                    switch(action.getMethod()){
                        case POST:
                           String response = restTemplate.postForObject(getUrl(endpoint), null, String.class);
                           System.out.println(response);
                    }
                });
            });
    }

    public String getUrl(String endpoint){
        Application application = eurekaClient.getApplication("gateway-server");
        InstanceInfo instanceInfo = application.getInstances().get(0);

        return String.format("http://%s:%d/%s", instanceInfo.getIPAddr(), instanceInfo.getPort(), endpoint);
    }
}