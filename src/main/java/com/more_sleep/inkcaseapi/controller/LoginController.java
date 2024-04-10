package com.more_sleep.inkcaseapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 登录控制器
 * @Author: lbj
 * @Date: 2024/3/24
 */

@Controller
@AllArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
