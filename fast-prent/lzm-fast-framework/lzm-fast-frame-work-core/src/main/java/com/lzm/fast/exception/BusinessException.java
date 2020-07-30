package com.lzm.fast.exception;


import com.lzm.fast.enums.RespCodeEnum;
import com.lzm.fast.enums.ResultCodeEnum;

/**
 * 业务错误
 *
 * @Description:
 * @Author: liuzhongming
 */
public class BusinessException extends ServiceException {
    public BusinessException(ResultCodeEnum resultCode) {
        super(resultCode);
    }

    public BusinessException(RespCodeEnum resultCode) {
        super(resultCode);
    }

    public BusinessException(Integer code, String msg) {
        super(code, msg);
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
