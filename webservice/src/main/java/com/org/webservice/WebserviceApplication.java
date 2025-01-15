package com.org.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
@ComponentScan(basePackages = {
         "com.org.*"
})
@EntityScan(basePackages = "com.org.library.data.model")
@EnableJpaRepositories(basePackages = "com.org.library.data.repository")
public class WebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }

}
