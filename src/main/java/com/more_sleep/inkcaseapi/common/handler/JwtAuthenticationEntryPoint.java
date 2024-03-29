package com.more_sleep.inkcaseapi.common.handler;

import com.alibaba.fastjson.JSON;
import com.more_sleep.inkcaseapi.common.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        String json = JSON.toJSONString(R.error("请先登录"));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
