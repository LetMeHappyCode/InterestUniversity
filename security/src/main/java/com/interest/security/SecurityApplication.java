package com.interest.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.interest"})
public class SecurityApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SecurityApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
