package com.lesstraffic.geolocationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> defaultErrorHandler(HttpClientErrorException e){
        return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    }
}