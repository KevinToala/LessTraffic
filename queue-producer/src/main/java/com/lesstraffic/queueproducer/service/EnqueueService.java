package com.lesstraffic.queueproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EnqueueService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${lesstraffic.topic.generic-enqueue}")
    private String TOPIC_ENQUEUE_NODE;

    public void genericEnqueue(Object object){
        kafkaTemplate.send(TOPIC_ENQUEUE_NODE, object);
    }
}
