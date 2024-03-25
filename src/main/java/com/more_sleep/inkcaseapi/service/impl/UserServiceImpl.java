package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.common.config.DBUserDetailsManager;
import com.more_sleep.inkcaseapi.entity.User;
import com.more_sleep.inkcaseapi.mapper.IUserMapper;
import com.more_sleep.inkcaseapi.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    private final DBUserDetailsManager dbUserDetailsManager;

    private final IUserMapper userMapper;

    public UserServiceImpl(DBUserDetailsManager dbUserDetailsManager, IUserMapper userMapper) {
        this.dbUserDetailsManager = dbUserDetailsManager;
        this.userMapper = userMapper;
    }

    @Override
    public void saveUserDetails(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getAccount())
                .password(user.getPassword())
                .build();
        /* 为什么调用createUser方法，而不是save方法？
         因为save方法是MyBatis-Plus提供的方法，用于保存实体类，而createUser方法是DBUserDetailsManager提供的方法，用于保存用户信息
         保存用户信息时，需要将用户信息保存到数据库中，同时也需要将用户信息保存到Spring Security中
         如果直接调用save方法，只会将用户信息保存到数据库中，而不会将用户信息保存到Spring Security中
         会怎么样？如果不将用户信息保存到Spring Security中，那么Spring Security就无法识别用户信息，也就无法进行认证和授权*/
//        userMapper.insert(user);
        dbUserDetailsManager.createUser(userDetails);
    }

    @Override
    public User getByName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User getByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        return userMapper.selectOne(queryWrapper);
    }
}
