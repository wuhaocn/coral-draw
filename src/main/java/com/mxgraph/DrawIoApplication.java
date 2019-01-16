package com.mxgraph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@ServletComponentScan
@SpringBootApplication
@EnableCaching
public class DrawIoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrawIoApplication.class, args);
    }
}