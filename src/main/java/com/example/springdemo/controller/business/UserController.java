package com.example.springdemo.controller.business;

import com.example.springdemo.controller.BaseController;
import com.example.springdemo.domain.model.UserModel;
import com.example.springdemo.domain.param.CommonQueryParam;
import com.example.springdemo.enums.GenderEnum;
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

    @GetMapping("/testRequestParamAnno")
    public void testRequestParamAnno(@RequestParam("gender") GenderEnum genderEnum) {
        // return genderEnum.getDesc();
    }

    @PostMapping("/testRequestBodyAnno")
    public String testRequestBodyAnno(@RequestBody UserModel user) {
        userService.save(user);
        return user.getGender().getDesc();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserModel user) {
        userService.save(user);
        return String.valueOf(user.getId());
    }

    @PostMapping("/query")
    public String queryUser(@RequestBody CommonQueryParam param) {
        UserModel user = userService.getById(param.getId());
        return user.getGender().getDesc();
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
