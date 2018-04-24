package com.lesstraffic.geolocationproducer.service;

import com.lesstraffic.geolocationproducer.dto.GeolocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GeolocationService {
    @Autowired
    private KafkaTemplate<String, GeolocationDTO> kafkaTemplate;

    @Value("${lesstraffic.geolocation.topic.enqueue-node}")
    private String TOPIC_ENQUEUE_GEOLOCATION_NODE;

    public void enqueueNode(GeolocationDTO geolocation){
        kafkaTemplate.send(TOPIC_ENQUEUE_GEOLOCATION_NODE, geolocation);
    }
}
