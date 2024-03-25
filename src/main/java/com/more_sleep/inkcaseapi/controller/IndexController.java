package com.more_sleep.inkcaseapi.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * test
 * @Author: lbj
 * @Date: 2024/3/24
 */

@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object authorities = authentication.getAuthorities();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", authentication.getName());
        map.put("authorities", authorities);
        return JSON.toJSONString(map);
    }
}
