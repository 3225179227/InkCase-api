package com.more_sleep.inkcaseapi.common.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.more_sleep.inkcaseapi.common.utils.BaseContext;
import com.more_sleep.inkcaseapi.mapper.IUserMapper;
import jakarta.annotation.Resource;
import com.more_sleep.inkcaseapi.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 从数据库中加载用户信息
 * @Author: lbj
 * @Date: 2024/3/24
 */
@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private IUserMapper userMapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();

        user.setAccount(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setDeleted(!userDetails.isEnabled());

        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * 根据用户名加载用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getAccount, username);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username + "用户不存在");
        } else {
            /*Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(() -> "USER_LIST");
            return new org.springframework.security.core.userdetails.User(
                    user.getAccount(),
                    user.getPassword(),
                    !user.getDeleted(),
                    true,
                    true,
                    true,
                    authorities);*/
            return org.springframework.security.core.userdetails.User.withUsername(user.getAccount())
                    .password(user.getPassword())
                    .roles(user.getAdmin() ? "ADMIN" : "USER")
                    .disabled(user.getDeleted())
                    .credentialsExpired(false)
                    .accountLocked(false)
                    .build();
        }
    }
}
