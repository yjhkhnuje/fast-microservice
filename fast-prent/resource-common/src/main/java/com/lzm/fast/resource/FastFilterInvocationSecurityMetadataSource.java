package com.lzm.fast.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: url获取角色码
 * @author: ZhongMing.Liu
 * @create: 2020/4/24 15:38
 */
@Component
@Slf4j
public class FastFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //object就FilterInvocation类型，其中包含用户请求的request信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        log.info("uri:" + request.getRequestURI() + " url:" + request.getRequestURL());
        //从url获取角色
        Set<ConfigAttribute> set = new HashSet<>();
        set.add(new SecurityConfig("admin"));
        set.add(new SecurityConfig("test"));
        return set;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
