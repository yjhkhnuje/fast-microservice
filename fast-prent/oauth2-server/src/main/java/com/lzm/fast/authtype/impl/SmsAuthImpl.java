package com.lzm.fast.authtype.impl;

import com.lzm.fast.authtype.AuthDto;
import com.lzm.fast.authtype.AuthMode;
import com.lzm.fast.authtype.AuthType;
import com.lzm.fast.entity.User;
import org.springframework.stereotype.Component;

/**
 * @description: 短信认证
 * @author: ZhongMing.Liu
 * @create: 2020/4/22 14:02
 */
@Component
public class SmsAuthImpl implements AuthMode {

    @Override
    public User authenticate(AuthDto authDto) {
        //根据手机号查询用户信息

        return null;
    }

    @Override
    public AuthType getAuthType() {
        return AuthType.SMS;
    }

    @Override
    public void prepare(AuthDto authDto) {
        //这里先验证短信验证码是否为空，是否过期等
    }

    @Override
    public void complete(AuthDto authDto) {

    }
}
