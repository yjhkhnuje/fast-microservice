package com.lzm.fast.controller;

import com.lzm.fast.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: test
 * @author: ZhongMing.Liu
 * @create: 2020/4/24 15:54
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/get")
    public String getUser(@RequestParam String id) {
        return "Test:" + id;
    }
}
