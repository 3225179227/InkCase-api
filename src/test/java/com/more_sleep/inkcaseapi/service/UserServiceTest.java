package com.more_sleep.inkcaseapi.service;

import com.alibaba.fastjson.JSON;
import com.more_sleep.inkcaseapi.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void test() {
        System.out.println(userService.list());
    }

    @Test
    public void user(){
        User user1 = new User();
        user1.setId(1L);
        user1.setAccount("张三");
        user1.setEmail("123123");
        user1.setAvatar("456456");
        System.out.println(JSON.toJSON(user1));
    }
}
