package com.lesstraffic.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import com.lesstraffic.core.model.Geolocalization;
import com.lesstraffic.core.services.GeolocalizationService;

@RestController
public class NodeController {
	@Autowired private GeolocalizationService geolocalizationService;
	@Autowired private KafkaTemplate<String, Geolocalization> kafkaTemplate;

	@Value("${lesstraffic.topic.enqueue.node}")
	private String TOPIC_ENQUEUE_NODE;

	/*@PostMapping("/nodes")
	public ResponseEntity<Geolocalization> insertNode(
			@RequestBody Geolocalization geolocalization)
	{
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(geolocalizationService.insertNode(geolocalization));
	}*/

	@PostMapping("enqueueNode")
    public ResponseEntity<Geolocalization> enqueueNode(
            @RequestBody Geolocalization geolocalization)
    {
		kafkaTemplate.send(TOPIC_ENQUEUE_NODE, geolocalization);

    	return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(geolocalization);
    }
}
