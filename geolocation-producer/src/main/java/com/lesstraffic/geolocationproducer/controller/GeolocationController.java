package com.lesstraffic.geolocationproducer.controller;

import com.lesstraffic.geolocationproducer.dto.GeolocationDTO;
import com.lesstraffic.geolocationproducer.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GeolocationController {
    @Autowired
    private GeolocationService geolocationService;

    @PostMapping("enqueueNode")
    public ResponseEntity<Void> enqueueNode(@RequestBody @Valid GeolocationDTO geolocation){
        geolocationService.enqueueNode(geolocation);

        return ResponseEntity
                .noContent()
                .build();
    }
    
    @GetMapping("hello")
	public ResponseEntity<String> sayHello(){
    	return ResponseEntity.ok("hello");
    }
	
	@GetMapping("hello/{name}")
	public ResponseEntity<String> sayHelloWithName(@PathVariable String name){
		return ResponseEntity.ok("hello " + name);
	}
}
