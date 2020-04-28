package com.lzm.fast.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 费用类型
 * </p>
 *
 * @author
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IbkFeeType {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 中文名称
     */
    private String cnName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 类型：1主费用 2子费用
     */
    private Integer type;

    /**
     * 交易类型：1加款 2扣款 0加款/扣款
     */
    private Integer tradeType;


    /**
     * 所属费用类型
     */
    private Integer parentId;

    /**
     * 所属费用类型code
     */
    private String parentCode;

    /**
     * 所属费用类型名称
     */
    private String parentName;

    /**
     * 创建网点
     */
    private Integer createNetworkId;

    /**
     * 创建网点名称
     */
    private String createNetworkName;

    /**
     * 是否可以编辑
     */
    private Integer isEdit;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 版本号
     */
    private String version;

    /**
     * 排序
     */
    private Integer sort;
}
