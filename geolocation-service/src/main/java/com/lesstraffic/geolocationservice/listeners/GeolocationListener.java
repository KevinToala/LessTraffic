package com.lesstraffic.geolocationservice.listeners;

import com.lesstraffic.geolocationservice.model.GeolocationDTO;
import com.lesstraffic.geolocationservice.services.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GeolocationListener {
	@Autowired private GeolocationService geolocationService;
	
	@KafkaListener(topics = "${lesstraffic.geolocation.topic.insert-node}", containerFactory = "kafkaListenerContainerFactory")
	public void insertNode(GeolocationDTO geolocationDTO){
		geolocationService.insertNode(geolocationDTO);
	}
}
