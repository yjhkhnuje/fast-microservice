package com.lzm.fast.filter;

import com.alibaba.druid.util.StringUtils;
import com.lzm.fast.authtype.AuthDto;
import com.lzm.fast.authtype.AuthModContext;
import com.lzm.fast.authtype.AuthMode;
import com.lzm.fast.authtype.AuthType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 验证模式过滤器
 * @author: ZhongMing.Liu
 * @create: 2020/4/22 14:14
 */
@Slf4j
public class AuthModeFilter extends GenericFilterBean {

    private static final String AUTH_TYPE_PARM_NAME = "auth_type";
    private static final String AUTH_USERNAME = "username";

    private static final String OAUTH_TOKEN_URL = "/oauth/token";

    private RequestMatcher requestMatcher;


    public AuthModeFilter() {
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST")
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (requestMatcher.matches(request)) {
            //设置集成登录信息
            AuthDto authDto = new AuthDto();
            String authType = request.getParameter(AUTH_TYPE_PARM_NAME);
            //没传时auth_type时，默认使用账号密码登录
            if (StringUtils.isEmpty(authType)) {
                authType = AuthType.USERNAME_PASSWORD.getCode();
            }
            authDto.setAuthType(authType);
            authDto.setUsername(request.getParameter(AUTH_USERNAME));
            authDto.setParameters(request.getParameterMap());
            AuthModContext.set(authDto);
            try {
                //预处理
                this.prepare(authDto);

                chain.doFilter(request, response);

                //后置处理
                this.complete(authDto);
            } finally {
                AuthModContext.clear();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * 验证后操作
     *
     * @param authDto
     */
    private void complete(AuthDto authDto) {
        AuthMode authMode = AuthModContext.getAuthMode(authDto.getAuthType());
        authMode.complete(authDto);
    }

    /**
     * 验证前操作
     *
     * @param authDto
     */
    private void prepare(AuthDto authDto) {
        AuthMode authMode = AuthModContext.getAuthMode(authDto.getAuthType());
        if (authMode == null) {
            log.error("授权类型authType:{} 为空", authDto.getAuthType());
            throw new OAuth2Exception("授权类型未找到");
        }
        authMode.prepare(authDto);
    }


}
