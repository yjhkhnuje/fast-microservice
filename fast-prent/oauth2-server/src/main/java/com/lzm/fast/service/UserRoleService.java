package com.lzm.fast.service;

import com.lzm.fast.dao.UserRoleDao;
import com.lzm.fast.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 用户角色
 * @author: ZhongMing.Liu
 * @create: 2020/4/23 11:37
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    public Set<GrantedAuthority> getRoleCodeList(long userId) {
        List<UserRole> list = userRoleDao.findByUserId(userId);
        if (!CollectionUtils.isEmpty(list)) {
            return list.stream().map(e -> new SimpleGrantedAuthority(e.getRoleCode())).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }


}
