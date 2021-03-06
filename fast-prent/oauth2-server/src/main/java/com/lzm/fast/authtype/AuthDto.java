package com.lzm.fast.authtype;

import lombok.Data;

import java.util.Map;

/**
 * @description: 授权参数
 * @author: ZhongMing.Liu
 * @create: 2020/4/21 18:22
 */
@Data
public class AuthDto {

    private String authType;
    private String username;
    private Map<String, String[]> parameters;

    public String getParameter(String paramter) {
        String[] values = this.parameters.get(paramter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }
}
