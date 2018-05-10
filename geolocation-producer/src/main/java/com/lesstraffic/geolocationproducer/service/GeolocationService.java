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

    @Value("${lesstraffic.geolocation.topic.insert-node}")
    private String GEOLOCATION_TOPIC_INSERT_NODE;

    public void enqueueNode(GeolocationDTO geolocation){
        kafkaTemplate.send(GEOLOCATION_TOPIC_INSERT_NODE, geolocation);
    }
}
