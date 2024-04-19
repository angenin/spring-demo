package com.example.springdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springdemo.domain.model.UserModel;
import com.example.springdemo.service.UserService;
import com.example.springdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 26436
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2024-04-19 10:41:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {

}




