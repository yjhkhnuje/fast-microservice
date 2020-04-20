package com.lzm.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @description: 启动类
 * @author: ZhongMing.Liu
 * @create: 2020/4/17 13:49
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.lzm.fast")
public class Oauth2ServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

}
