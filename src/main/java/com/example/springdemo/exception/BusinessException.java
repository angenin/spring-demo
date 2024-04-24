package com.example.springdemo.exception;

import com.example.springdemo.enums.ResultEnum;

/**
 * 业务异常
 */
public class BusinessException extends BaseException {

    protected BusinessException(ResultEnum re) {
        super(re);
    }

}
