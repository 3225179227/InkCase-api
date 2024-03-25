package com.more_sleep.inkcaseapi.clear;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.more_sleep.inkcaseapi.entity.User;
import com.more_sleep.inkcaseapi.mapper.IUserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RemoveTest {

    @Autowired
    private IUserMapper userMapper;

    // 清除user数据库中所有account字段中含test的数据
    @Test
    public void removeUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("account", "test");
        userMapper.delete(queryWrapper);
    }

}
