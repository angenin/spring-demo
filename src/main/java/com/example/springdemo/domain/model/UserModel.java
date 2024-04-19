package com.example.springdemo.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.springdemo.constant.BasicConstant;
import com.example.springdemo.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户表
 * @TableName t_user
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value ="t_user")
public class UserModel extends BaseModel {

    /**
     * ASSIGN_ID：默认自动生成（雪花算法）
     * TODO 默认的雪花算法生成规则，后续研究
     * com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator#nextId(java.lang.Object)
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Short age;

    /**
     * 性别（M男，F女）
     */
    private String gender;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = BasicConstant.TIME_ZONE, pattern = BasicConstant.DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = BasicConstant.TIME_ZONE, pattern = BasicConstant.DATE_TIME_FORMAT)
    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}