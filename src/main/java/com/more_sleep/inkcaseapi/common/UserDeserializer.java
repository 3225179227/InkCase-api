package com.more_sleep.inkcaseapi.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.more_sleep.inkcaseapi.entity.User;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {
    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value == null || value.isEmpty()) {
            return null; // 或者返回一个新的User对象
        }
        // 如果value不为空，那么可以按照你的需求来反序列化
        // 这里只是一个示例，可能需要根据你的实际情况进行修改
        User user = new User();
        // 设置user的属性
        return user;
    }
}