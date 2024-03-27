package com.more_sleep.inkcaseapi.common.handler;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.Map;


/**
 * 登录失败时的处理
 * @Author: lbj
 * @Date: 2024/3/24
 */
public class AAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String localizedMessage = exception.getLocalizedMessage();
        Map<Object, Object> result = Map.of("code", 1, "msg", localizedMessage);

        response.setContentType("application/json;charset=utf-8");
        String json = JSON.toJSONString(result);
        response.getWriter().write(json);
    }
}
