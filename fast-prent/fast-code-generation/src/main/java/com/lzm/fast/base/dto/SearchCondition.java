package com.lzm.fast.base.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 动态查询对象
 * @author: ZhongMing.Liu
 * @create: 2020/4/27 18:17
 */
@Data
public class SearchCondition implements Serializable {

    private List<Order> orders = new ArrayList<>();

    private Integer current;

    private Integer size;

    private List<Condition> conditions = new ArrayList<>();

    private String keyword;


    @JsonIgnore
    @JSONField(serialize = false)
    public Condition getCondition(String property) {
        for (Condition condition : conditions) {
            if (condition.getProperty().equals(property)) {
                return condition;
            }
        }
        return null;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public String getConditionValue(String property) {
        Condition condition =  getCondition(property);
        if (condition != null){
            return condition.getValue();
        }
        return null;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public Condition removeCondition(String property){
        Condition c = null;
        List<Integer> removeList = new ArrayList<>();
        for (int i = 0; i < this.conditions.size(); i++) {
            Condition condition = this.conditions.get(i);
            if (property.equals(condition.getProperty())) {
                c = condition;
                removeList.add(i);
            }
        }
        for (Integer i : removeList) {
            this.conditions.remove(i.intValue());
        }
        return c == null ? new Condition() : c;
    }

    /**
     * 增加条件
     * @param condition
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public void addCondition(Condition condition) {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
    }

    /**
     * 增加排序
     * @param order
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public void addOrder(Order order){
        if(this.orders == null) {
            this.orders = new ArrayList<>();
        }
        this.orders.add(order);
    }

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
