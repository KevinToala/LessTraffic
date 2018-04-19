package com.lesstraffic.queueconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "${lesstraffic.topic.enqueue.node}")
    public void enqueuNode(Object object){
        System.out.println(object);
    }
}
