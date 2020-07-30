package com.lzm.fast.exception;


import com.lzm.fast.enums.RespCodeEnum;
import com.lzm.fast.enums.ResultCodeEnum;
import lombok.Data;

/**
 * @description: 轻微异常
 * @author: ZhongMing.Liu
 * @create: 2020/4/24 15:27
 */
@Data
public class SlightException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    public SlightException(ResultCodeEnum resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public SlightException(RespCodeEnum resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public SlightException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public SlightException(String msg) {
        super(msg);
    }

    public SlightException(Throwable throwable) {
        super(throwable);
    }

    public SlightException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
