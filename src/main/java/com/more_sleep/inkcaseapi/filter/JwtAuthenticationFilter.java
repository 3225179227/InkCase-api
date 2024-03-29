package com.more_sleep.inkcaseapi.filter;

import com.more_sleep.inkcaseapi.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
//@WebFilter
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtUtils jwtUtils;

    private final UserDetailsService userDetailService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsService userDetailService) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(jwtUtils.getHeader());
        // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
        // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
        /*if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }*/
        if (jwt == null || jwt.isBlank()) {
            chain.doFilter(request, response);
            return;
        }

//        Claims claims = jwtUtils.getClaimsByToken(jwt);
//        if (claims == null || jwtUtils.isTokenExpired(claims)) {
//            throw new JwtException("Invalid or expired token");
//        }

        Claims claim = jwtUtils.getClaimsByToken(jwt);
        if (claim == null) {
            throw new JwtException("token 异常");
        }
        if (jwtUtils.isTokenExpired(claim)) {
            throw new JwtException("token 已过期");
        }

        String username = claim.getSubject();
        // 获取用户的权限等信息

//        SysUser sysUser = sysUserService.getByUsername(username);
//        User user = userService.getByName(username);


        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        // 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);

    }
}
