package com.lzm.fast.resource;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/24 15:27
 */
@Component
public class FastAccessDecisionManager implements AccessDecisionManager {

    private List<AccessDecisionVoter<? extends Object>> decisionVoters;


    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configAttributes || configAttributes.size() <= 0) {
            return;
        }
        ConfigAttribute c;
        String needRole;
        for (ConfigAttribute configAttribute : configAttributes) {
            c = configAttribute;
            needRole = c.getAttribute();
            //authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("访问被拒绝，权限不足");
    }

    /**
     * 复制默认方法，使得@PreAuthorize("hasRole('ROLE_ADMIN')") 可用
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        if (!CollectionUtils.isEmpty(this.decisionVoters)) {
            for (AccessDecisionVoter voter : this.decisionVoters) {
                if (voter.supports(attribute)) {
                    return true;
                }
            }
        }
        System.out.println("------------def -----");
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        if (!CollectionUtils.isEmpty(this.decisionVoters)) {
            for (AccessDecisionVoter voter : this.decisionVoters) {
                if (!voter.supports(clazz)) {
                    return false;
                }
            }
        }
        System.out.println("------------def -----");
        return true;
    }
}