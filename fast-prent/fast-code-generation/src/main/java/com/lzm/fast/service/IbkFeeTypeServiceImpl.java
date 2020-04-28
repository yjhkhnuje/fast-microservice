package com.lzm.fast.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzm.fast.base.BasePageDto;
import com.lzm.fast.entity.IbkFeeType;
import com.lzm.fast.mapper.IbkFeeTypeDao;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/27 20:13
 */
@Service
public class IbkFeeTypeServiceImpl extends ServiceImpl<IbkFeeTypeDao, IbkFeeType> implements IbkFeeTypeService {

    @Override
    public Page<IbkFeeType> get(BasePageDto<IbkFeeType> dto) {
       //return baseMapper.selectPage(dto.getPage(),new QueryWrapper<IbkFeeType>().eq("code",dto.getDto().getCode()));
        return baseMapper.selectPage(dto.getPage(), new LambdaQueryWrapper<IbkFeeType>().eq(IbkFeeType::getCode, dto.getDto().getCode()));
    }
}
