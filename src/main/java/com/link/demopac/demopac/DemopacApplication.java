package com.link.demopac.demopac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.link.demopac"})
public class DemopacApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemopacApplication.class, args);
    }

}
