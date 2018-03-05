package com.czajor.carserviceportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringRunner extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringRunner.class, args);
    }
}