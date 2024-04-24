package com.example.springdemo.exception;

import com.example.springdemo.enums.ResultEnum;

/**
 * 业务异常
 */
public class BusinessException extends BaseException {

    public BusinessException(ResultEnum re, String... desc) {
        super(re, desc);
    }

}
