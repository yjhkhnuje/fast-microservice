package com.lzm.fast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 代码生成管理
 * @author: ZhongMing.Liu
 * @create: 2020/4/26 14:57
 */
@SpringBootApplication
@MapperScan("com.lzm.fast.*mapper")
public class CodeGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGenerationApplication.class, args);
    }

}
