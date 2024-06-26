package com.example.springdemo.enums.business;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.example.springdemo.handle.EnumDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 性别枚举
 */
@Getter
@ToString
@AllArgsConstructor
@JsonFormat(shape= JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = EnumDeserializer.class)
public enum GenderEnum implements BaseEnum {

    MALE("M", "男"),
    FEMALE("F", "女");

    @EnumValue
    private String value;

    private String desc;

}
