package com.example.springdemo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.example.springdemo.util.ReflectUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 基础枚举
 * <p/>
 * 用于扫描、序列化、反序列化实际枚举类
 */
public interface BaseEnum {

    /**
     * 获取枚举
     * @param enumTypeClazz 枚举类型类
     * @param value 值
     * @param nullThrExFlag 为空是否抛异常
     * @return
     * @param <T>
     */
    public static <T extends BaseEnum> T getEnum(Class<T> enumTypeClazz, Object value, boolean nullThrExFlag) {
        if (enumTypeClazz == null || value == null) {
            return null;
        }

        T[] enumConstants = enumTypeClazz.getEnumConstants();
        for (T enumConstant : enumConstants) {
            // 获取有枚举有@EnumValue注解的字段
            String fieldName = Arrays.stream(enumTypeClazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(EnumValue.class))
                    .findFirst().map(Field::getName).orElse(null);
            // 枚举未配置@EnumValue属性，报错
            if (StringUtils.isBlank(fieldName)) {
                // TODO 系统异常
                throw new RuntimeException("枚举属性值未配置");
            }
            // 通过属性的get方法获取枚举对应的属性值
            Object enumValue = null;
            try {
                enumValue = MethodUtils.invokeMethod(enumConstant, ReflectUtil.getMethodNameByFieldName(fieldName, null));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                // TODO 系统异常
                throw new RuntimeException("枚举处理异常");
            }
        }

        // 为空抛异常，全局异常处理，获取可在枚举上加注解，通过读取类上的注解里的内容显示参数名
        if (nullThrExFlag) {
            throw new RuntimeException("[" + enumTypeClazz.getSimpleName() + "]参数错误[" + String.valueOf(value) + "]");
        }
        return null;
    }

}
