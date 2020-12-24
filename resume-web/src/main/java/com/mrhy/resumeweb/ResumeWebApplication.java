package com.mrhy.resumeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mrhy
 * @date 2020/12/22 20:39
 * Copyright (C), 2018-2020
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ResumeWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeWebApplication.class, args);
        System.out.println("api服务启动了");
    }
}
