package com.example.springdemo.exception;

import com.example.springdemo.enums.ResultEnum;
import lombok.Data;

/**
 * 公共异常
 */
@Data
public class BaseException extends RuntimeException {

    /**
     * 错误码
     */
    protected final Integer errorCode;

    /**
     * 错误信息
     */
    protected final String errorMessage;

    protected BaseException(ResultEnum re) {
        if (re.getStatus()) {
            throw new IllegalArgumentException("ResultEnum status must be false");
        }
        this.errorCode = re.getCode();
        this.errorMessage = re.getMessage();
    }

}
