package com.lesstraffic.geolocationservice.listeners;

import com.lesstraffic.geolocationservice.dto.Geolocation;
import com.lesstraffic.geolocationservice.services.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GeolocationListener {
	@Autowired private GeolocationService geolocationService;
	
	@KafkaListener(topics = "${lesstraffic.geolocation.topic.insert-node}")
	public void insertNode(Geolocation geolocation){
		geolocationService.insertNode(geolocation);
	}
}
