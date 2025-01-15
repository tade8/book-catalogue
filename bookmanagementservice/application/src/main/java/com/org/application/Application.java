package com.org.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.org.library")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
