package com.lesstraffic.queueproducer.controller;

import com.lesstraffic.queueproducer.service.EnqueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnqueueController {
    @Autowired
    private EnqueueService enqueueService;

    @PostMapping("genericEnqueue")
    public ResponseEntity<Void> genericEnqueue(@RequestBody Object object){
        enqueueService.genericEnqueue(object);

        return ResponseEntity
                .noContent()
                .build();
    }
}
