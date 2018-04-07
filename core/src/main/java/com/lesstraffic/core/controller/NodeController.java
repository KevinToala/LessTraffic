package com.lesstraffic.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lesstraffic.core.model.Geolocalization;
import com.lesstraffic.core.services.GeolocalizationService;

@RestController
public class NodeController {
	@Autowired
	private GeolocalizationService geolocalizationService;
	
	@GetMapping("/nodes")
	public ResponseEntity<List<Geolocalization>> getNodes(){
		return ResponseEntity.ok(geolocalizationService.getNodes());
	}
	
	@PostMapping("/nodes")
	public ResponseEntity<Geolocalization> insertNode(
			@RequestBody Geolocalization geolocalization)
	{
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(geolocalizationService.insertNode(geolocalization));
	}
}
