package com.more_sleep.inkcaseapi.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMapper {
    // 编写IUserMapper的测试方法
    // 1. 编写测试方法testSelectById

    @Autowired
    private IUserMapper userMapper;

    @Test
    public void testSelectById() {
        // 2. 调用IUserMapper查询所有用户
        // 3. 打印查询结果
        System.out.println(userMapper.selectById(1));

    }
}
