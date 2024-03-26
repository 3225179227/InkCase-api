package com.more_sleep.inkcaseapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.more_sleep.inkcaseapi.entity.User;
import com.more_sleep.inkcaseapi.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理控制器
 * @Author: lbj
 * @Date: 2024/3/24
 */

@RestController
@RequestMapping("/manage")
public class ManageController {

    private final IUserService userService;

    public ManageController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> list() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(
                User::getAccount,
                User::getCreateDate,
                User::getEmail,
                User::getNickname,
                User::getLastLogin,
                User::getMobilePhoneNumber);
        return userService.list();
    }

//    @PostMapping("/user/add")
//    public void add(@RequestBody User user) {
//        userService.save(user);
//    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
