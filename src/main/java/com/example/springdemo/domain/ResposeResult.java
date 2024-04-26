package com.example.springdemo.domain;

import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResposeResult<T> extends BaseObject {

    private static final long serialVersionUID = -6942129987234487344L;

    /**
     * 状态信息
     */
    private final Boolean status;
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 提示信息
     */
    private final String message;
    /**
     * 数据
     */
    private T data;

    /**
     * 自定义返回
     * @param status
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> ResposeResult<T> result(Boolean status, Integer code, String message, T data){
        return ResposeResult.<T>builder().status(status).code(code).message(message).data(data).build();
    }

    /**
     * 自定义返回
     * @param re
     * @param data
     * @return
     */
    public static <T> ResposeResult<T> result(ResultEnum re, T data){
        return result(re.getStatus(), re.getCode(), re.getMessage(), data);
    }

    /**
     * 自定义返回
     * @param re
     * @return
     */
    public static ResposeResult result(ResultEnum re){
        return result(re, null);
    }

    /**
     * 成功返回
     * @return
     */
    public static ResposeResult success(){
        return result(ResultEnum.SUCCESS);
    }

    /**
     * 成功返回
     * @param data
     * @return
     */
    public static <T> ResposeResult<T> success(T data){
        return result(ResultEnum.SUCCESS, data);
    }

    /**
     * 异常返回
     * @param be
     * @return
     */
    public static ResposeResult exception(BaseException be) {
        return result(false, be.getErrorCode(), be.getErrorMessage(), null);
    }

}