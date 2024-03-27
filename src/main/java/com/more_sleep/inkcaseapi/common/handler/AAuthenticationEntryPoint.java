package com.more_sleep.inkcaseapi.common.handler;

import com.alibaba.fastjson.JSON;
import com.more_sleep.inkcaseapi.common.Code;
import com.more_sleep.inkcaseapi.common.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;

/**
 * 未登录时的处理
 * @Author: lbj
 * @Date: 2024/3/24
 */
public class AAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String json = JSON.toJSONString(R.error(Code.USER_NOT_LOGGED_IN));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
