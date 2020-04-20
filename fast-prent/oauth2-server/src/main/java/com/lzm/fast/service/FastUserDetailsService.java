package com.lzm.fast.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/20 16:06
 */
@Service
@Slf4j
public class FastUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("当前登录用户名：{}", username);
        com.lzm.fast.entity.User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("未找到用户");
        }
        return buildUser(user);
    }


    /**
     * 用户转换
     *
     * @param userEntity
     * @return
     */
    private User buildUser(com.lzm.fast.entity.User userEntity) {
        if (userEntity != null) {
            //是否启用
            boolean isEnable = userEntity.getStatus() == 1 ? true : false;
            //TODO 角色
            Set<GrantedAuthority> set = new HashSet<>();
            set.add(new SimpleGrantedAuthority("admin"));
            set.add(new SimpleGrantedAuthority("user1"));
            return new User(userEntity.getUsername(), userEntity.getPassword(), isEnable, true, true, isEnable, set);
        }
        return null;
    }
}
