package com.lesstraffic.queueconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class QueueConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueConsumerApplication.class, args);
    }
}
