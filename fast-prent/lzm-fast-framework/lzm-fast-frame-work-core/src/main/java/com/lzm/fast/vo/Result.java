package com.lzm.fast.vo;


import com.lzm.fast.enums.RespCodeEnum;
import com.lzm.fast.enums.ResultCodeEnum;
import com.lzm.fast.exception.ServiceException;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 返回码VO
 * @author: ZhongMing.Liu
 * @create: 2020/4/24 15:27
 */
@Data
@NoArgsConstructor
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(ResultCodeEnum ResultCodeEnum) {
        if (ResultCodeEnum != null) {
            this.code = ResultCodeEnum.getCode();
            this.msg = ResultCodeEnum.getMsg();
        }
    }


    public T result() {
        if (ResultCodeEnum.SUCCESS.getCode() != code) {
            throw new ServiceException(code, msg);
        }
        return data;
    }

    public boolean isSuccess() {
        return this.code != null && ResultCodeEnum.SUCCESS.getCode() == this.code;
    }


    private Result(T data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = ResultCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }


    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功时候的调用
     */
    public static Result<Void> success() {
        return new Result<Void>(ResultCodeEnum.SUCCESS);
    }

    /**
     * 成功时候的调用
     */
    public static Result<Void> success(Integer code, String msg) {
        return new Result<Void>(code, msg);
    }


    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(ResultCodeEnum ResultCodeEnum) {
        return new Result<T>(ResultCodeEnum);
    }

    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(RespCodeEnum respCodeEnum) {
        return new Result<>(respCodeEnum.getCode(), respCodeEnum.getMsg());
    }

    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<T>(code, msg);
    }

}
