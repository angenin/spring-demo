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
     * 对象创建成功
     */
    CREATED(201, true, "对象创建成功"),
    /**
     * 请求已经被接受
     */
    ACCEPTED(202, true, "请求已经被接受"),
    /**
     * 操作已经执行成功，但是没有返回数据
     */
    NO_CONTENT(204, true, "操作已经执行成功，但是没有返回数据"),
    // /**
    //  * 资源已被移除
    //  */
    // MOVED_PERM(301, "资源已被移除"),
    // /**
    //  * 重定向
    //  */
    // SEE_OTHER(303, "重定向"),
    // /**
    //  * 资源没有被修改
    //  */
    // NOT_MODIFIED(304, "资源没有被修改"),
    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    BAD_REQUEST(400, false, "参数列表错误（缺少，格式不匹配）"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, false, "未授权"),
    /**
     * 访问受限，授权过期
     */
    FORBIDDEN(403, false, "访问受限，授权过期"),
    /**
     * 资源，服务未找到
     */
    NOT_FOUND(404, false, "资源，服务未找！"),
    /**
     * 不允许的http方法
     */
    BAD_METHOD(405, false, "不允许的http方法"),
    /**
     * 资源冲突，或者资源被锁
     */
    CONFLICT(409, false, "资源冲突，或者资源被锁"),
    /**
     * 不支持的数据，媒体类型
     */
    UNSUPPORTED_TYPE(415, false, "不支持的数据，媒体类型"),
    /**
     * 系统内部错误
     */
    ERROR(500, false, "系统内部错误"),
    /**
     * 接口未实现
     */
    NOT_IMPLEMENTED(501, false, "接口未实现"),
    /**
     * 系统警告消息
     */
    WARN(601, false,"系统警告消息")

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
