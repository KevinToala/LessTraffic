package com.lesstraffic.geolocationproducer.controller;

import com.lesstraffic.geolocationproducer.dto.GeolocationDTO;
import com.lesstraffic.geolocationproducer.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeolocationController {
    @Autowired
    private GeolocationService geolocationService;

    @PostMapping("enqueueNode")
    public ResponseEntity<Void> enqueueNode(@RequestBody GeolocationDTO geolocation){
        geolocationService.enqueueNode(geolocation);

        return ResponseEntity
                .noContent()
                .build();
    }
}
