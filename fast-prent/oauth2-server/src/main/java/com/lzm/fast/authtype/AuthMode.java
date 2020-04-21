package com.lzm.fast.authtype;

import com.lzm.fast.entity.User;

/**
 * @description: 授权方式
 * @author: ZhongMing.Liu
 * @create: 2020/4/21 18:18
 */
public interface AuthMode {

    /**
     * 获取授权对象
     *
     * @param authDto
     * @return
     */
    User authenticate(AuthDto authDto);

    /**
     * 获取授权类型
     *
     * @return
     */
    AuthType getAuthType();


    /**
     * 进行预处理
     *
     * @param authDto
     */
    void prepare(AuthDto authDto);


    /**
     * 认证结束后执行
     *
     * @param authDto
     */
    void complete(AuthDto authDto);
}
