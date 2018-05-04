package com.lesstraffic.logtraceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

@EnableEurekaClient
@EnableZipkinServer
@SpringBootApplication
public class LogTraceServerApplication {

    public static void main(String[] args){
        SpringApplication.run(LogTraceServerApplication.class, args);
    }
}
