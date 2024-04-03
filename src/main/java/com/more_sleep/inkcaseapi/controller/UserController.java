package com.more_sleep.inkcaseapi.controller;



import com.alibaba.fastjson.annotation.JSONType;
import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.entity.User;
import com.more_sleep.inkcaseapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @Author: lbj
 * @Date: 2024/3/24
 */

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/user-info")
    public R<User> getUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 获取用户名
        return R.success(userService.getByName(username));
    }

    @PostMapping("/register")
    public R<User> register(@RequestBody User user, @RequestParam("code") String code) {
        return userService.saveUserDetails(user, code);
    }
}
