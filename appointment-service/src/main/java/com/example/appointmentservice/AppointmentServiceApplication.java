package com.example.appointmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppointmentServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "appointment-service");
        SpringApplication.run(AppointmentServiceApplication.class, args);
    }

}
