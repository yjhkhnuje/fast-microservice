package com.lzm.fast.authtype.impl;

import com.lzm.fast.authtype.AuthDto;
import com.lzm.fast.authtype.AuthType;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @description: 验证码模式
 * @author: ZhongMing.Liu
 * @create: 2020/4/22 13:53
 */
@Component
public class VerificationCodeAuthImpl extends UserNameAuthImpl {

    @Override
    public void prepare(AuthDto authDto) {
        String vcCode = authDto.getParameter("vc_code");
        //TODO 动态验证码验证
        if (!"123".equals(vcCode)){
            throw new OAuth2Exception("验证码错误");
        }
    }

    @Override
    public AuthType getAuthType() {
        return AuthType.VERIFICATION_CODE;
    }

}
