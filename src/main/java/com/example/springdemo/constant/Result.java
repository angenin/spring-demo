package com.example.springdemo.constant;

import lombok.Data;

@Data
public class Result {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

}
