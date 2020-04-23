package com.lzm.fast.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @description: 用户角色
 * @author: ZhongMing.Liu
 * @create: 2020/4/23 11:30
 */
@Entity
@Table(name = "sys_user_role")
@Data
public class UserRole {

    /**
     * mysql数据库主键策略IDENTITY
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String roleCode;

    @Column
    private Long roleId;

    @Column
    private Long createId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTs;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTs;


}
