package com.lzm.fast.dao;

import com.lzm.fast.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/20 17:20
 */
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @param status
     * @return
     */
    User findByUsernameAndStatus(String username, Integer status);
}
