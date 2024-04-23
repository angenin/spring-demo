package com.example.springdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置类
 */
@Configuration
@MapperScan("com.example.springdemo.mapper")
public class DbConfig {


}
