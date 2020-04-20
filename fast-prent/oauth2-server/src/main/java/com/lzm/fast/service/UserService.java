package com.lzm.fast.service;

import com.lzm.fast.dao.UserDao;
import com.lzm.fast.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/20 17:21
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     *
     * @param username
     * @return
     */
    public User findByUsername(String username){
        return userDao.findByUsernameAndStatus(username,1);
    }

}
