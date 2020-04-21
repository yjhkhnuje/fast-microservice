package com.lzm.fast.authtype.impl;

import com.lzm.fast.authtype.AuthDto;
import com.lzm.fast.authtype.AuthMode;
import com.lzm.fast.authtype.AuthType;
import com.lzm.fast.entity.User;
import com.lzm.fast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 用户密码形式
 * @author: ZhongMing.Liu
 * @create: 2020/4/21 18:27
 */
@Component
public class UserNameAuthImpl implements AuthMode {


    @Autowired
    private UserService userService;

    @Override
    public User authenticate(AuthDto authDto) {
        return userService.findByUsername(authDto.getUsername());
    }

    @Override
    public AuthType getAuthType() {
        return AuthType.USERNAME_PASSWORD;
    }

    @Override
    public void prepare(AuthDto authDto) {

    }

    @Override
    public void complete(AuthDto authDto) {

    }
}
