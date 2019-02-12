package com.mxgraph;

import org.helium.boot.spring.annotation.EnableHeliumConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableHeliumConfiguration
public class DrawIoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrawIoApplication.class, args);
    }
}