package com.lzm.fast.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzm.fast.base.BasePageDto;
import com.lzm.fast.entity.IbkFeeType;
import com.lzm.fast.service.IbkFeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IbkFeeTypeService ibkFeeTypeService;


    @RequestMapping("/get")
    public String get(@RequestParam(required = false) String id) {
        return "xxxxxxx:" + id + " custom: " + key;
    }

    @PostMapping("get1")
    public Page<IbkFeeType> query(@RequestBody BasePageDto<IbkFeeType> dto) {
        return ibkFeeTypeService.get(dto);
    }
}
