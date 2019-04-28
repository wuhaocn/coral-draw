package com.mxgraph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//启用JPA审计
@EnableJpaAuditing
public class CoralDrawApplication {
     public static void main(String[] args) {
        SpringApplication.run(CoralDrawApplication.class, args);
    }
}