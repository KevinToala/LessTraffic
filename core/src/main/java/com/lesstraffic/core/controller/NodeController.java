package com.lesstraffic.core.controller;

import com.lesstraffic.core.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lesstraffic.core.model.Geolocalization;

@RestController
public class NodeController {
	@Autowired
	private ProducerService producerService;

	@PostMapping("enqueueNode")
    public ResponseEntity<Geolocalization> enqueueNode(
            @RequestBody Geolocalization geolocalization)
    {
		producerService.enqueueNode(geolocalization);

    	return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(geolocalization);
    }

    @GetMapping("hello")
	public String hello(){
		return "Hello!!!";
	}
}
