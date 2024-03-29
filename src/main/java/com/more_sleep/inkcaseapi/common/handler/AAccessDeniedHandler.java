package com.more_sleep.inkcaseapi.common.handler;

import com.alibaba.fastjson.JSON;
import com.more_sleep.inkcaseapi.common.Code;
import com.more_sleep.inkcaseapi.common.R;
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
@Deprecated
public class AAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String json = JSON.toJSONString(R.error(Code.PERMISSION_NO_ACCESS.getCode(), "权限不足"));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
