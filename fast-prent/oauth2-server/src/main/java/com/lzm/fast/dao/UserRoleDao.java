package com.lzm.fast.dao;

import com.lzm.fast.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description: 用户角色
 * @author: ZhongMing.Liu
 * @create: 2020/4/23 11:34
 */
public interface UserRoleDao extends JpaRepository<UserRole, String> {
    /**
     * 根据用户查询角色code
     *
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(long userId);
}
