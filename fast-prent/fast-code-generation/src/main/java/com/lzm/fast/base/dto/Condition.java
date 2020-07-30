package com.lzm.fast.base.dto;

import lombok.Data;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/27 18:22
 */
@Data
public class Condition {
    /**
     * 字段
     */
    private String property;

    /**
     * 操作符
     */
    private String op;

    /**
     * 值
     */
    private String value;

}
