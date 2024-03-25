package com.more_sleep.inkcaseapi.common.handler;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;

/**
 * 权限不足时的处理
 * 请求未携带token或者token无效时的处理
 * @Author: lbj
 * @Date: 2024/3/24
 */

public class AAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HashMap<Object, Object> result = new HashMap<>();
        result.put("code", 403);
        result.put("msg", "权限不足");

        String json = JSON.toJSONString(result);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
