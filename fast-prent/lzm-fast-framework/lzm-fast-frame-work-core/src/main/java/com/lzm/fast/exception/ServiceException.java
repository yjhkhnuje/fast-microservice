package com.lzm.fast.exception;


import com.lzm.fast.enums.RespCodeEnum;
import com.lzm.fast.enums.ResultCodeEnum;
import lombok.Data;

/**
 * @description: 服务异常
 * @author: ZhongMing.Liu
 * @create: 2020/4/24 15:27
 */
@Data
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    public ServiceException(ResultCodeEnum resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public ServiceException(RespCodeEnum resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }


    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }


    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    public ServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
