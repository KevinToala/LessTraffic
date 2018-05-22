package com.lesstraffic.geolocationproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GeolocationProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeolocationProducerApplication.class, args);
    }
}
