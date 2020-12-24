package com.mrhy.resumeserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author mrhy
 * @date 2020/12/21 20:44
 * Copyright (C), 2018-2020
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mrhy.resumeserver.mapper")
public class ResumeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeServerApplication.class, args);
        System.out.println("服务启动了");
    }
}
