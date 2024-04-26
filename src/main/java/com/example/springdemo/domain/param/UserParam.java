package com.example.springdemo.domain.param;

import com.example.springdemo.constant.ValidGroup;
import com.example.springdemo.domain.BaseObject;
import com.example.springdemo.enums.GenderEnum;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserParam extends BaseObject {

    private static final long serialVersionUID = -6295194357403220665L;

    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空", groups = {ValidGroup.Query.class, ValidGroup.Update.class, ValidGroup.Delete.class})
    private Long id;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ValidGroup.Add.class})
    private String name;

    /**
     * 年龄
     */
    @Min(value = 0, message = "年龄不能小于0", groups = {ValidGroup.Add.class})
    @NotNull(message = "年龄不能为空", groups = {ValidGroup.Add.class})
    private Short age;

    /**
     * 性别（M男，F女）
     */
    @NotNull(message = "性别不能为空", groups = {ValidGroup.Add.class})
    private GenderEnum gender;

    // 如果校验的参数类型是对象，而对象里的参数也得校验，需要在对象属性上加上@Valid注解

}
