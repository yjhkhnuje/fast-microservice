package com.lzm.fast.authtype;

import com.lzm.fast.util.SpringUtil;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/22 14:34
 */
public class AuthModContext {

    private static Object lock = new Object();

    /**
     * 验证参数线程传递
     */
    private static ThreadLocal<AuthDto> holder = new ThreadLocal<>();

    /**
     * 所有的验证方式
     */
    private static Map<String, AuthMode> authModeMap = null;


    public static void set(AuthDto authDto) {
        holder.set(authDto);
    }

    public static AuthDto get() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }

    public static AuthMode getAuthMode(String authType) {
        if (authModeMap == null) {
            synchronized (lock) {
                if (authModeMap == null) {
                    authModeMap = SpringUtil.getBeansOfType(AuthMode.class).stream().collect(Collectors.toMap(e -> e.getAuthType().getCode(), e -> e));
                }
            }
        }
        return authModeMap.get(authType);
    }
}
