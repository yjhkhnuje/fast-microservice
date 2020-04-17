package com.lzm.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description: 启动类
 * @author: ZhongMing.Liu
 * @create: 2020/4/17 15:03
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterCenterApplication.class, args);
    }

}
