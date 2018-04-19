package com.lesstraffic.queueconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "${lesstraffic.topic.generic-enqueue}")
    public void genericEnqueue(Object object){
        System.out.println(object);
    }
}
