package com.lzm.fast.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: test
 * @author: ZhongMing.Liu
 * @create: 2020/4/26 14:58
 */
@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${aliyun.sdk.key:null}")
    private String key;


    @RequestMapping("/get")
    public String get(@RequestParam(required = false) String id) {
        return "xxxxxxx:" + id + " custom: " + key;
    }


}
