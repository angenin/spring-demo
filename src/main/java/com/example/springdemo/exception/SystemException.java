package com.example.springdemo.exception;

import com.example.springdemo.enums.ResultEnum;

/**
 * 系统异常
 */
public class SystemException extends BaseException {

    public SystemException(ResultEnum re, String... desc) {
        super(re, desc);
    }

}
