package com.example.springdemo.handle;

import com.example.springdemo.domain.ResposeResult;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.BusinessException;
import com.example.springdemo.exception.SystemException;
import com.example.springdemo.exception.VerifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理校验异常（自定义）
     * @param e
     * @return
     */
    @ExceptionHandler(VerifyException.class)
    public ResposeResult handleVerifyException(VerifyException e) {
        printlnErrorLog("校验异常", e);
        return ResposeResult.exception(e);
    }

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResposeResult handleBusinessException(BusinessException e) {
        printlnErrorLog("业务异常", e);
        return ResposeResult.exception(e);
    }

    /**
     * 处理系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public ResposeResult handleBusinessException(SystemException e) {
        printlnErrorLog("系统异常", e);
        return ResposeResult.exception(e);
    }

    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, ConstraintViolationException.class,
            BindException.class, MissingServletRequestParameterException.class})
    public ResposeResult handleValidException(Exception e) {
        printlnErrorLog("参数校验异常", e);
        String errorMsg = "";
        if (e instanceof MethodArgumentNotValidException) {
            errorMsg = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).collect(Collectors.joining("; "));

        } else if (e instanceof ValidationException) {
            errorMsg = ((ConstraintViolationException) e).getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));

        } else if (e instanceof BindException) {
            errorMsg = ((BindException) e).getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));

        } else if (e instanceof MissingServletRequestParameterException) {
            errorMsg = ((MissingServletRequestParameterException) e).getParameterName();
        }

        return ResposeResult.exception(new VerifyException(ResultEnum.BAD_PARAM, errorMsg));
    }

    /**
     * 处理错误的请求异常
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    public ResposeResult handleRequestException(Exception e) {
        printlnErrorLog("错误的请求异常", e);
        return ResposeResult.result(ResultEnum.BAD_REQUEST);
    }

    /**
     * 404异常
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResposeResult handle404Exception(NoHandlerFoundException e) {
        printlnErrorLog("404异常", e);
        return ResposeResult.result(ResultEnum.NOT_FOUND);
    }

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResposeResult handleException(Exception e) {
        printlnErrorLog("系统异常", e);
        return ResposeResult.result(ResultEnum.SYS_ERROR);
    }

    /**
     * 异常日志
     * @param title
     * @param e
     */
    private void printlnErrorLog(String title, Exception e) {
        StackTraceElement stackTrace = e.getStackTrace()[0];
        log.error("\n----------------------- exception info -----------------------\n\t" +
                        "exception title: {}\n\t" +
                        "exception type: {}\n\t" +
                        "exception message: {}\n\t" +
                        "file name: {}\n\t" +
                        "class name: {}\n\t" +
                        "method name: {}\n\t" +
                        "line number: {}\n\t" +
                        "full exception info: ",
                title, e.getClass().getName(), e.getMessage(), stackTrace.getFileName(), stackTrace.getClassName(),
                stackTrace.getMethodName(), stackTrace.getLineNumber(), e);
    }

}
