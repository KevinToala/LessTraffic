package com.lesstraffic.queueproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class QueueProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueProducerApplication.class, args);
    }
}
