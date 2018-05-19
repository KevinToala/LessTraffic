package com.lesstraffic.geolocationproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class GeolocationProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeolocationProducerApplication.class, args);
    }
    
    @GetMapping("/test")
	public String test(){
    	return "ok!!";
    }
}
