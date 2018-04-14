package com.lesstraffic.core.services;

import com.lesstraffic.core.model.Geolocalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, Geolocalization> kafkaTemplate;

    @Value("${lesstraffic.topic.enqueue.node}")
    private String TOPIC_ENQUEUE_NODE;

    public void enqueueNode(Geolocalization geolocalization){
        kafkaTemplate.send(TOPIC_ENQUEUE_NODE, geolocalization);

    }
}
