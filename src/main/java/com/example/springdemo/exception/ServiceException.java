package com.example.springdemo.exception;

import com.example.springdemo.enums.ResultEnum;

/**
 * 服务异常
 */
public class ServiceException extends BaseException {

    public ServiceException(ResultEnum re, String... desc) {
        super(re, desc);
    }

}
