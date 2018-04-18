package com.lesstraffic.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GenericController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "generic")
    public ResponseEntity<Void> execute(HttpServletRequest request){
        return ResponseEntity.ok(null);
    }
}
