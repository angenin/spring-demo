package com.example.springdemo.demos.web.controller;

import com.alibaba.fastjson.JSON;
import com.example.springdemo.constant.ResultConstant;
import com.example.springdemo.demos.web.entity.User;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static Map<String, User> USER_MAP = new HashMap<>(16);

    @GetMapping("/add")
    public String modifyUser(@RequestParam String name, @RequestParam(required = false) Integer age) {
        Long id = RandomUtils.nextLong();
        String userId = String.valueOf(id);
        User user = User.builder().id(id).name(name).age(age).build();
        USER_MAP.put(userId, user);
        return userId;
    }

    @GetMapping("/modify")
    public String modifyUser(@RequestParam String userId, @RequestBody User u) {
        // TODO 报错，待处理 @RequestParam @RequestBody
        User user = USER_MAP.get(userId);
        if (ObjectUtils.isNotEmpty(user)) {
            return ResultConstant.ResultMsg.error;
        }
        user.setName(u.getName());
        user.setAge(u.getAge());
        return ResultConstant.ResultMsg.success;
    }

    @GetMapping("/query/{userId}")
    public String modifyUser(@PathVariable String userId) {
        User user = USER_MAP.get(userId);
        if (ObjectUtils.isEmpty(user)) {
            return ResultConstant.ResultMsg.error;
        }
        return JSON.toJSONString(user);
    }

}
