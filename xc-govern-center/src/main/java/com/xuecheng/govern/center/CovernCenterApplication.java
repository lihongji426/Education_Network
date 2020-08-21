package com.xuecheng.govern.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 标识此工程是一个EurekaServer
public class CovernCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovernCenterApplication.class, args);
    }
}
