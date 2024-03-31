package com.more_sleep.inkcaseapi.common.handler;

import com.alibaba.fastjson.JSON;
import com.more_sleep.inkcaseapi.common.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 登录成功时的处理
 * @Author: lbj
 * @Date: 2024/3/24
 */

@Deprecated
public class AAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /*HashMap<Object, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "登录成功");
        authentication.getPrincipal() //返回的是当前登录用户的信息
        authentication.getCredentials() //返回的是当前登录用户的密码
        authentication.getAuthorities() //返回的是当前登录用户的权限
        authentication.getPrincipal();
        result.put("data", authentication.getPrincipal());*/


        /*System.out.println(authentication.getCredentials());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getDetails());*/
        response.setContentType("application/json;charset=UTF-8");

        String json = JSON.toJSONString(R.success("登录成功"));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
