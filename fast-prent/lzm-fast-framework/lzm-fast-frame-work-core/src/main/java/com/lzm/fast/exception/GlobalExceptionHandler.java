package com.lzm.fast.exception;


import com.lzm.fast.enums.ResultCodeEnum;
import com.lzm.fast.vo.Result;
import com.sun.corba.se.impl.io.TypeMismatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Description: 全局异常处理<br>
 * 注:{@link ServiceException} 处理error日志 所有抛出ServiceException的日志都将打印error级别的日志
 * 若需打印warn日志请使用{@link BusinessException}
 * @Author: liuzhongming
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static String RESPONSE_DATA = "responseData";

    /**
     * 违反约束，javax扩展定义
     *
     * @param ex 违反约束异常
     * @return Result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result resolveConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getInvalidValue() + " " + violation.getMessage() + "\n");
        }
        String errorMessage = strBuilder.toString();
        log.warn("参数错误:", ex);
        //TODO 配合链路日志
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (errorMessage != null) {
            Result result = Result.error(ResultCodeEnum.PARAMS_IS_INVALID.getCode(), ResultCodeEnum.PARAMS_IS_INVALID.getMsg() + ":" + errorMessage);
            request.setAttribute(RESPONSE_DATA, result);
            return result;
        }
        Result result = Result.error(ResultCodeEnum.PARAMS_IS_INVALID.getCode(), ResultCodeEnum.PARAMS_IS_INVALID.getMsg() + ":" + ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }

    /**
     * 参数无效，如JSON请求参数违反约束
     *
     * @param ex 参数无效异常
     * @return Result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        //获取错误信息
        String errorMessage = buildMessages(ex.getBindingResult());
        log.debug("参数无效:", ex);
        //TODO 配合链路日志
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (errorMessage != null) {
            Result result = Result.error(ResultCodeEnum.PARAMS_IS_INVALID.getCode(), ResultCodeEnum.PARAMS_IS_INVALID.getMsg() + ":" + errorMessage);
            request.setAttribute(RESPONSE_DATA, result);
            return result;
        }
        Result result = Result.error(ResultCodeEnum.PARAMS_IS_INVALID.getCode(), ResultCodeEnum.PARAMS_IS_INVALID.getMsg() + ":" + ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;

    }

    /**
     * 绑定失败，如表单对象参数违反约束
     *
     * @param ex 对象参数违反约束异常
     * @return Result
     */
    @ExceptionHandler(BindException.class)
    public Result resolveBindException(BindException ex) {
        //获取错误信息
        String errorMessage = buildMessages(ex.getBindingResult());
        log.debug("参数绑定错误:", ex);
        //TODO 配合链路日志
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (errorMessage != null) {
            Result result = Result.error(ResultCodeEnum.PARAMS_IS_INVALID.getCode(), ResultCodeEnum.PARAMS_IS_INVALID.getMsg() + ":" + errorMessage);
            request.setAttribute(RESPONSE_DATA, result);
            return result;
        }
        Result result = Result.error(ResultCodeEnum.PARAMS_IS_INVALID.getCode(), ResultCodeEnum.PARAMS_IS_INVALID.getMsg() + ":" + ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }

    /**
     * 参数缺失
     *
     * @param ex 参数缺失异常
     * @return Result
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result resolveMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.debug("参数缺失:", ex);
        //TODO 配合链路日志
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Result result = Result.error(ResultCodeEnum.PARAMS_NOT_COMPLETE.getCode(), ResultCodeEnum.PARAMS_NOT_COMPLETE.getMsg() + ":" + ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;

    }

    /**
     * 参数类型不匹配
     *
     * @param ex 参数类型不匹配异常
     * @return Result
     */
    @ExceptionHandler(TypeMismatchException.class)
    public Result resolveTypeMismatchException(TypeMismatchException ex) {
        log.debug("参数类型不匹配:", ex);
        //TODO 配合链路日志
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Result result = Result.error(ResultCodeEnum.PARAMS_TYPE_ERROR.getCode(), ResultCodeEnum.PARAMS_TYPE_ERROR.getMsg() + ":" + ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }

//    /**
//     * 查询mongo异常
//     *
//     * @param ex 查询mongo异常
//     * @return Result
//     * @author wangzhengxi
//     * 
//     * 
//     */
//    @ExceptionHandler(MongoQueryException.class)
//    public Result mongoQueryException(MongoQueryException ex) {
//        logger.error("查询mongo异常:", ex);
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//TODO 配合链路日志
//        Result result = Result.error(ResultCodeEnum.MONGO_QUERY_TYPE_ERROR.getCode(), ResultCodeEnum.MONGO_QUERY_TYPE_ERROR.getMsg() + ":" + ex.getMessage());
//        request.setAttribute(RESPONSE_DATA, result);
//        return result;
//    }
//
//    /**
//     * 新增mongo异常
//     *
//     * @param ex 新增mongo异常，主键重复
//     * @return Result
//     * @author wangzhengxi
//     * 
//     * 
//     */
//    @ExceptionHandler(MongoWriteException.class)
//    public Result mongoQueryException(MongoWriteException ex) {
//        logger.error("新增mongo异常:", ex);
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//TODO 配合链路日志
//        Result result = Result.error(ResultCodeEnum.MONGO_ADD_TYPE_ERROR.getCode(), ResultCodeEnum.MONGO_ADD_TYPE_ERROR.getMsg() + ":" + ex.getMessage());
//        request.setAttribute(RESPONSE_DATA, result);
//        return result;
//    }

    /**
     * 新增feign调用超时异常
     *
     * @param ex 新增mongo异常，主键重复
     * @return Result
     */
    @ExceptionHandler(SocketTimeoutException.class)
    public Result resolveSocketTimeoutException(SocketTimeoutException ex) {
        log.error("远程调用超时:", ex);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Result result = Result.error(ResultCodeEnum.INTERFACE_REQUEST_TIMEOUT.getCode(), ResultCodeEnum.INTERFACE_REQUEST_TIMEOUT.getMsg() + ":" + ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }

    /**
     * 自定义异常
     *
     * @param ex 自定义异常
     * @return Result
     * @author linhaibo
     * @date 2019-06-20
     */
    @ExceptionHandler(ServiceException.class)
    public Result resolveServiceException(ServiceException ex) {
        log.error("自定义异常", ex);
        //TODO 配合链路日志
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Result result = Result.error(ex.getCode(), ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }


    /**
     * 业务异常处理
     *
     * @param ex 业务异常
     * @return Result
     * @date 2019-06-20
     */
    @ExceptionHandler(BusinessException.class)
    public Result resolveBusinessException(BusinessException ex) {
        log.warn("业务异常", ex);
        //TODO 配合链路日志
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Result result = Result.error(ex.getCode(), ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }

    /**
     * 请求方式异常
     *
     * @param ex
     * @return Result
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result resolveMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn("请求方式异常:", ex);
        //TODO 配合链路日志
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Result result = Result.error(ResultCodeEnum.HTTP_REQUEST_METHOD_ERROR);
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }

    /**
     * 只记录info级别的异常处理
     *
     * @param ex SlightException
     * @return Result
     * @author zuting ou
     * @date 2019/12/9
     */
    @ExceptionHandler(SlightException.class)
    public Result resolveSlightException(SlightException ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Result result = Result.error(ex.getCode(), ex.getMessage());
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }

    /**
     * 系统内部错误
     *
     * @param ex 系统内部错误
     * @return Result
     * @author linhaibo
     * @date 2019-06-20
     */
    @ExceptionHandler(Exception.class)
    public Result resolveException(Exception ex) {
        log.error("系统内部错误:", ex);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//TODO 配合链路日志
        Result result = Result.error(ResultCodeEnum.SYSTEM_INNER_ERROR);
        request.setAttribute(RESPONSE_DATA, result);
        return result;
    }

    /**
     * 构建错误信息
     *
     * @param result 错误异常信息
     * @return java.lang.String
     * @author linhaibo
     * @date 2019-06-20
     */
    private String buildMessages(BindingResult result) {
        StringBuilder resultBuilder = new StringBuilder();

        List<ObjectError> errors = result.getAllErrors();
        if (errors != null && errors.size() > 0) {
            for (ObjectError error : errors) {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    String fieldName = fieldError.getField();
                    String fieldErrMsg = fieldError.getDefaultMessage();
                    resultBuilder.append(fieldName).append(" ").append(fieldErrMsg).append(";");
                }
            }
        }
        return resultBuilder.toString();
    }
}
