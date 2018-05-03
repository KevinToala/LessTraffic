package com.lesstraffic.geolocationservice.controller;

import com.lesstraffic.geolocationservice.model.Geolocation;
import com.lesstraffic.geolocationservice.services.geolocalization.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GeolocationController {
	@Autowired
	private GeolocationService geolocationService;

	@PostMapping("insertNode")
    public ResponseEntity<Geolocation> insertNode(@RequestBody Geolocation geolocation){
	    geolocationService.insertNode(geolocation);

    	return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(geolocation);
    }

    @GetMapping("hello/{name}")
	public Map<String, String> hello(@PathVariable String name){
		Map<String, String> result = new HashMap<>();
		result.put("content", "Hello, " + name);
		return result;
	}
}
