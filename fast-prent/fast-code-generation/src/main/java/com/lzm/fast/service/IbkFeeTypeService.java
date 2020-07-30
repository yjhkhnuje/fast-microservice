package com.lzm.fast.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzm.fast.base.dto.BasePageDto;
import com.lzm.fast.entity.IbkFeeType;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/28 14:40
 */
public interface IbkFeeTypeService extends IService<IbkFeeType> {
    Page<IbkFeeType> get(BasePageDto<IbkFeeType> dto);
}
