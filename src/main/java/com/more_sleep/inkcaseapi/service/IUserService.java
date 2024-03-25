package com.more_sleep.inkcaseapi.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.more_sleep.inkcaseapi.entity.User;
import org.springframework.stereotype.Service;

public interface IUserService extends IService<User> {
    void saveUserDetails(User user);

    User getByName(String username);

    User getByEmail(String email);
}
