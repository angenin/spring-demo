package com.example.springdemo.handle.enums;

import com.example.springdemo.enums.BaseEnum;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 枚举反序列化器
 */
public class EnumDeserializer extends JsonDeserializer<BaseEnum> {

    /**
     * 根据参数值获取对应的枚举
     * @param p Parsed used for reading JSON content
     * @param ctxt Context that can be used to access information about
     *   this deserialization activity.
     *
     * @return
     * @throws IOException
     * @throws JacksonException
     */
    @Override
    public BaseEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        // TODO 同时也解决了参数值传枚举名称和下标能找到对应枚举的问题
        final String param = p.getText();// 参数值
        final JsonStreamContext parsingContext = p.getParsingContext();// 字段对应的信息
        final String currentName = parsingContext.getCurrentName();// 参数值对应的字段名
        final Object currentValue = parsingContext.getCurrentValue();// 参数值对应的Model
        try {
            final Field declaredField = currentValue.getClass().getDeclaredField(currentName);// 反射获取字段信息
            final Class<BaseEnum> targetType = (Class<BaseEnum>) declaredField.getType();// 通过字段信息获取对应的枚举的Class
            BaseEnum baseEnum = BaseEnum.getEnum(targetType, param);// 获取对应的枚举
            if (ObjectUtils.isEmpty(baseEnum)) {
                // 为空抛异常，全局异常处理
                throw new RuntimeException("[" + currentName + "]参数错误");
            }
            return baseEnum;
        } catch (NoSuchFieldException e) {
            // TODO 反序列化失败异常，后续异常处理配置好后处理
            throw new RuntimeException("[" + currentName + "]参数错误");
        }
    }

}
