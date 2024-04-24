package com.example.springdemo.util;

import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.VerifyException;

import java.util.Optional;

/**
 * 断言工具类<p/>
 */
public class AssertUtil {

    public static void notNull(Object obj, ResultEnum re) {
        Optional.ofNullable(obj).orElseThrow(() -> new VerifyException(re));
    }

}
