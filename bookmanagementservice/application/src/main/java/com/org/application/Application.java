package com.org.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.*;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication(scanBasePackages = "com.org.library")
@EnableJpaRepositories(basePackages = "com.org.library.data.repository")
@EntityScan(basePackages = "com.org.library.data.model")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
