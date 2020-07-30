package com.lzm.fast.base.dto;

import lombok.Data;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/27 18:22
 */
@Data
public class Order {
    /**
     * 字段
     */
    private String property;

    /**
     * 排序方式
     */
    private String direction = "asc";

}
