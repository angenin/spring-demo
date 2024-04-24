package com.example.springdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    /**
     * 处理成功
     */
    SUCCESS(200, true, "成功"),
    /**
     * 请求异步处理中
     */
    ACCEPTED(201, true, "请求异步处理中"),
    /**
     * 参数校验错误
     */
    BAD_PARAM(400, false, "参数校验错误[%s]"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, false, "未授权"),
    /**
     * 访问受限，授权过期
     */
    FORBIDDEN(403, false, "访问受限，授权过期"),
    /**
     * 资源服务不存在
     */
    NOT_FOUND(404, false, "资源服务不存在"),
    /**
     * 错误的请求
     */
    BAD_REQUEST(405, false, "错误的请求"),
    /**
     * 系统内部错误
     */
    SYS_ERROR(500, false, "系统内部错误"),

    ;

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态信息
     */
    private final Boolean status;

    /**
     * 提示信息
     */
    private final String message;

}
