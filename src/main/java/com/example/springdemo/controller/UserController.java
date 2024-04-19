package com.example.springdemo.controller;

import com.example.springdemo.domain.model.UserModel;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String modifyUser(@RequestBody UserModel user) {
        userService.save(user);
        return String.valueOf(user.getId());
    }

    //@GetMapping("/modify")
    //public String modifyUser(@RequestParam String userId, @RequestBody UserEntity u) {
    //    // TODO 报错，@RequestParam 和 @RequestBody 不要同时使用
    //    User user = USER_MAP.get(userId);
    //    if (ObjectUtils.isNotEmpty(user)) {
    //        return ResultConstant.ResultMsg.error;
    //    }
    //    user.setName(u.getName());
    //    user.setAge(u.getAge());
    //    return ResultConstant.ResultMsg.success;
    //}
    //
    //@GetMapping("/query/{userId}")
    //public String modifyUser(@PathVariable String userId) {
    //    User user = USER_MAP.get(userId);
    //    if (ObjectUtils.isEmpty(user)) {
    //        return ResultConstant.ResultMsg.error;
    //    }
    //    return JSON.toJSONString(user);
    //}

}
