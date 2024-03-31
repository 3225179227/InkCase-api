package com.more_sleep.inkcaseapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.more_sleep.inkcaseapi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface IUserMapper extends BaseMapper<User> {
}
