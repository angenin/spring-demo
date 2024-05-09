package com.example.springdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum SmsEnum {

    EXPIRATION_NOTICE("2149923", "临期通知", Arrays.asList("车牌号（掩码）", "服务类型", "距离过期天数")),

    ;

    /**
     * 模板id
     */
    private final String templateId;

    /**
     * 模板名称
     */
    private final String templateName;

    /**
     * 内容说明（注：每个内容长度最大为6）
     */
    private final List<String> contentDesc;

}
