package com.more_sleep.inkcaseapi.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 用户状态
 * @Author: lbj
 * @Date: 2024/3/24
 */

@Getter
public enum UserStatus {

    normal(0, "正常状态"),
    blocked(1, "封禁状态");

    @EnumValue
    private final Integer code;
    private final String info;

    private UserStatus(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
