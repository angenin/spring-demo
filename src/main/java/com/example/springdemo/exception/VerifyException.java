package com.example.springdemo.exception;

import com.example.springdemo.enums.ResultEnum;

/**
 * 校验异常
 */
public class VerifyException extends BaseException {

    public VerifyException(ResultEnum re) {
        super(re);
    }

}
