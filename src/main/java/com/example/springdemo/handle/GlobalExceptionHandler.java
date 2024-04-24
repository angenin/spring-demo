package com.example.springdemo.handle;

import com.example.springdemo.domain.ResposeResult;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.BusinessException;
import com.example.springdemo.exception.VerifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

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
    public ResposeResult handleVerifyException(VerifyException e) {
        log.error("校验异常：", e);
        return ResposeResult.exception(e);
    }

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResposeResult handleBusinessException(BusinessException e) {
        log.error("业务异常：", e);
        return ResposeResult.exception(e);
    }

    /**
     * 处理参数校验异常（@Valid注解或者@Validated进行请求参数校验）
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResposeResult handleParamException(MethodArgumentNotValidException e) {
        log.error("参数校验异常：", e);
        return ResposeResult.result(ResultEnum.BAD_PARAM, e.getMessage());
    }

    /**
     * 处理错误的请求异常（@Valid注解或者@Validated进行请求参数校验）
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    public ResposeResult handleRequestException(Exception e) {
        log.error("错误的请求异常：", e);
        return ResposeResult.result(ResultEnum.BAD_REQUEST);
    }

    /**
     * 404异常
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResposeResult handle404Exception(NoHandlerFoundException e) {
        log.error("404异常：", e);
        return ResposeResult.result(ResultEnum.NOT_FOUND);
    }

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResposeResult handleException(Exception e) {
        log.error("系统异常：", e);
        return ResposeResult.result(ResultEnum.SYS_ERROR);
    }

}
