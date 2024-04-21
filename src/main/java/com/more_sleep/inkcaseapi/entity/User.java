package com.more_sleep.inkcaseapi.entity;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @Author: lbj
 * @Date: 2024/3/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@JSONType(includes = {"account", "nickname", "avatar", "email"})
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -4454737765850239378L;

//    @JsonIgnore
    private Long id;

    // 用户名
    @NotBlank
    private String account;

    /**
     * 使用md5(username + original password + salt)加密存储
     */
    // 密码
    @NotBlank
//    @JsonIgnore
    private String password;

    /**
     * 头像
     */
    private String avatar;

    @NotBlank
    private String email;  // 邮箱

    private String nickname;

    private String mobilePhoneNumber;


    /**
     * 加密密码时使用的种子
     */
    @JsonIgnore
    private String salt;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;


    /**
     * 最后一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    /**
     * 系统用户的状态
     */
    private UserStatus status = UserStatus.normal;

    /**
     * 是否是管理员
     */
    private Boolean admin = false;

    /**
     * 逻辑删除flag
     */
    private Boolean deleted = Boolean.FALSE;

}
