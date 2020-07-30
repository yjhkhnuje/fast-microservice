package com.lzm.fast.base.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 自定义判断查询对象
 * @author: ZhongMing.Liu
 * @create: 2020/4/27 17:37
 */
@Data
public class BasePageDto<T> implements Serializable {
    /**
     * 查询dto
     */
    private T dto;

    /**
     * 分页显示数量
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 获取 page
     *
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public Page getPage() {
        return PageUtil.getPage(current, size, true);
    }


}
