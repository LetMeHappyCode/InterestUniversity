package com.interest.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.interest"})
public class CommunityApplication {
    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext context = SpringApplication.run(CommunityApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
