package com.example.springdemo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.example.springdemo.handle.enums.EnumDeserializer;
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

    MALE((short)1, "男"),
    FEMALE((short)0, "女");

    @EnumValue
    @Getter
    private Short value;
    @Getter
    private String desc;

}
