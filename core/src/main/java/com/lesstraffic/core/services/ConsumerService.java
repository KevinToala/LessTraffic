package com.lesstraffic.core.services;

import com.lesstraffic.core.model.Geolocalization;
import com.lesstraffic.core.services.geolocalization.GeolocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @Autowired
    private GeolocalizationService geolocalizationService;

    @KafkaListener(topics = "${lesstraffic.topic.enqueue.node}")
    public void enqueuNode(Geolocalization geolocalization){
        geolocalizationService.insertNode(geolocalization);
    }
}
