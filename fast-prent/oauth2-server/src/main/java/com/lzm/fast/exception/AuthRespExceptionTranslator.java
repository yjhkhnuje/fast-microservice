package com.lzm.fast.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @description: 异常转换
 * @author: ZhongMing.Liu
 * @create: 2020/4/23 11:52
 */
@Component
@Slf4j
public class AuthRespExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        return super.translate(e);
    }
}
