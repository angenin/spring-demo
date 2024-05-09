package com.example.springdemo.handle;

import com.example.springdemo.config.WebMvcConfig;
import com.example.springdemo.enums.business.BaseEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;

/**
 * 字符串转枚举工厂类
 * @see WebMvcConfig#addFormatters(FormatterRegistry)
 */
@Component
public class EnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return source -> BaseEnum.getEnum(targetType, StringUtils.trim(source), true);
    }

}
