package com.example.springdemo.controller.business;

import com.example.springdemo.constant.ValidGroup;
import com.example.springdemo.controller.BaseController;
import com.example.springdemo.domain.model.UserModel;
import com.example.springdemo.domain.param.CommonQueryParam;
import com.example.springdemo.domain.param.UserParam;
import com.example.springdemo.enums.GenderEnum;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/testRequestParamAnno")
    public void testRequestParamAnno(@RequestParam(value = "gender", required = false) GenderEnum genderEnum) {
        // @RequestParam默认需要，可以通过参数来控制是否必传
        // return genderEnum.getDesc();
    }

    @PostMapping("/testRequestBodyAnno")
    public String testRequestBodyAnno(@Valid @RequestBody UserParam user) {
        // @Valid无法对加了group参数进行校验
        userService.save(UserModel.builder().name(user.getName()).age(user.getAge()).gender(user.getGender()).build());
        return user.getGender().getDesc();
    }

    @PostMapping("/add")
    public String addUser(@Validated(ValidGroup.Add.class) @RequestBody UserParam user) {
        // @Validated的分组必须和参数的group匹配，否则无法校验（要么一起加且相同，要么都不加group，否则无法校验）
        userService.save(UserModel.builder().name(user.getName()).age(user.getAge()).gender(user.getGender()).build());
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
