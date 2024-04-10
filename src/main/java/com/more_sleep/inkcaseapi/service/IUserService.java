package com.more_sleep.inkcaseapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService extends IService<User> {
    R<User> saveUserDetails(User user, String code);

    User getByName(String username);

    User getByEmail(String email);

    UserDetails loadUserByUsername(String s);
}
