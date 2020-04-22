package com.lzm.fast.authtype;

/**
 * @description: 认证类型
 * @author: ZhongMing.Liu
 * @create: 2020/4/21 18:29
 */
public enum AuthType {

    USERNAME_PASSWORD("PASSWORD", "密码模式"),
    SMS("SMS", "短信认证模式"),
    VERIFICATION_CODE("VC", "验证码");

    private String code;

    private String describe;


    AuthType(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }


    public String getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
