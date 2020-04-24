package com.lzm.fast.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/24 14:41
 */
@EnableResourceServer
@Configuration
@Slf4j
public class FastResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    FastAccessDecisionManager fastAccessDecisionManager;
    @Autowired
    FastFilterInvocationSecurityMetadataSource fastFilterInvocationSecurityMetadataSource;

    @Value("${jwt.signing.key:lzm-fast}")
    private String jwtSigningKey;

    @Bean
    public FilterSecurityInterceptor securityAccessInterceptor() {
        FilterSecurityInterceptor securityInterceptor = new FilterSecurityInterceptor();
        securityInterceptor.setAccessDecisionManager(fastAccessDecisionManager);
        securityInterceptor.setSecurityMetadataSource(fastFilterInvocationSecurityMetadataSource);
        return securityInterceptor;
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(new JwtTokenStore(resourceJwtAccessTokenConverter()));
    }

    @Bean
    public JwtAccessTokenConverter resourceJwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(jwtSigningKey);
        return jwtAccessTokenConverter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //排除Swagger文档
        http.authorizeRequests().antMatchers("/v2/api-docs").permitAll().and().csrf().disable()
                .authorizeRequests().anyRequest().authenticated().filterSecurityInterceptorOncePerRequest(false)
/*                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        // 请求需要权限
                        o.setSecurityMetadataSource(fastFilterInvocationSecurityMetadataSource);
                        // 权限判断
                        o.setAccessDecisionManager(fastAccessDecisionManager);
                        return o;
                    }
                });*/
                .and().addFilterAfter(securityAccessInterceptor(), FilterSecurityInterceptor.class);
        log.info("Security Access Control is enabled on Resource Server Application");
    }


}

