package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.common.config.DBUserDetailsManager;
import com.more_sleep.inkcaseapi.entity.User;
import com.more_sleep.inkcaseapi.mapper.IUserMapper;
import com.more_sleep.inkcaseapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    private final DBUserDetailsManager dbUserDetailsManager;

    private final IUserMapper userMapper;

    private final RedisTemplate<Object, Object> redisTemplate;

//    private final PasswordEncoder passwordEncoder;


    @Override
    public R<User> saveUserDetails(User user, String code) {
        // 验证邮箱是否已经注册
        User userByEmail = getByEmail(user.getEmail());
        if (userByEmail != null) {
            return R.error("邮箱已经注册");
        } else {
            //验证邮箱验证码是否正确
            ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
            Object codeInRedis = valueOperations.get(user.getEmail());

            if (codeInRedis == null || !codeInRedis.equals(code)) {
                return R.error("验证码错误");
            } else {
                user.setDeleted(false);
                user.setAdmin(false);

                UserDetails userDetails = org.springframework.security.core.userdetails.User
                        .withDefaultPasswordEncoder()
//                        .builder()
//                        .password(passwordEncoder.encode(user.getPassword()))
                        .username(user.getAccount())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();
                /* 为什么调用createUser方法，而不是save方法？
         因为save方法是MyBatis-Plus提供的方法，用于保存实体类，而createUser方法是DBUserDetailsManager提供的方法，用于保存用户信息
         保存用户信息时，需要将用户信息保存到数据库中，同时也需要将用户信息保存到Spring Security中
         如果直接调用save方法，只会将用户信息保存到数据库中，而不会将用户信息保存到Spring Security中
         会怎么样？如果不将用户信息保存到Spring Security中，那么Spring Security就无法识别用户信息，也就无法进行认证和授权*/
                dbUserDetailsManager.createUser(userDetails);
                return R.success(user);
            }
        }
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
