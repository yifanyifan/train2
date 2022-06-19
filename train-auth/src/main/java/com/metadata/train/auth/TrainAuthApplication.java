package com.metadata.train.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@ComponentScan({"com.metadata.train", "com.metadata.train.auth"})
public class TrainAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainAuthApplication.class, args);
    }

}
