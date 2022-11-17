package com.example.microorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroOrderServiceApplication.class, args);
    }

}
