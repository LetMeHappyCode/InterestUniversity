package com.interest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
//@ComponentScan({"com.interest.adapter"})
public class AdapterApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(AdapterApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
