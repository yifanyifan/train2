package com.metadata.train.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@ComponentScan({"com.metadata.train", "com.metadata.train.sys"})
@MapperScan({"com.metadata.train.*.mapper"})
public class TrainSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainSysApplication.class, args);
    }

}
