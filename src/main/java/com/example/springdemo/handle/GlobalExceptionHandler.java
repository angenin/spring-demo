package com.example.springdemo.handle;

import com.example.springdemo.domain.ResposeResult;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.BusinessException;
import com.example.springdemo.exception.VerifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(VerifyException.class)
    public ResposeResult handleException(VerifyException e) {
        log.error("校验异常：", e);
        return ResposeResult.exception(e);
    }

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResposeResult handleException(BusinessException e) {
        log.error("业务异常：", e);
        return ResposeResult.exception(e);
    }

    /**
     * TODO 异常处理
     * 1. 404异常，待处理
     * https://cloud.tencent.com/developer/article/2212371
     * https://cloud.tencent.com/developer/article/1889357
     * https://cloud.tencent.com/developer/article/1823685
     * 2. 特定异常处理：https://www.cnblogs.com/coderacademy/p/17994318
     */

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResposeResult handleException(Exception e) {
        log.error("系统异常：", e);
        return ResposeResult.result(ResultEnum.ERROR);
    }

}
