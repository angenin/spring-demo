package com.example.springdemo.enums;

import java.util.Objects;

/**
 * 基础枚举
 * <p/>
 * 用于扫描、序列化、反序列化实际枚举类
 */
public interface BaseEnum {

    /**
     * 序列化
     *
     * @return 不允许返回 null
     */
    Short getCode();

    /**
     * 反序列化
     *
     * @param enumType 实际枚举类型
     * @param code    当前值
     * @param <T>      实现 {@link BaseEnum} 接口
     * @return 枚举常量
     */
    static <T extends BaseEnum> T getEnum(Class<T> enumType, Object code) {
        if (enumType == null || code == null) {
            return null;
        }

        T[] enumConstants = enumType.getEnumConstants();
        for (T enumConstant : enumConstants) {
            Object enumValue = enumConstant.getCode();
            if (Objects.equals(enumValue, code) || Objects.equals(enumValue.toString(), code.toString())) {
                return enumConstant;
            }
        }

        return null;
    }

}
