package com.more_sleep.inkcaseapi.filter;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.more_sleep.inkcaseapi.common.utils.JwtUtils;
import com.more_sleep.inkcaseapi.entity.User;
import com.more_sleep.inkcaseapi.mapper.IUserMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class JwtAthFilter extends OncePerRequestFilter {

    private final IUserMapper userDao;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 获取 HTTP 请求头部中的 Authorization 字段
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String jwtToken;

        // 如果 Authorization 字段不存在或者不符合 Bearer Token 的格式，则跳过该过滤器
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 提取 JWT Token，并从中获取用户邮箱
        jwtToken = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwtToken);

        // 如果用户邮箱不为空且未进行身份验证
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getAccount, userEmail);
            // 根据用户邮箱从 UserDao 中查找用户
            User user = userDao.selectOne(wrapper);
            System.out.println(user);

            // 创建 UserDetails 对象包括权限
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getAccount())
                    .password(user.getPassword())
                    .roles(user.getAdmin() ? "ADMIN" : "USER")
                    .disabled(user.getDeleted())
                    .credentialsExpired(false)
                    .accountLocked(false)
                    .build();

            System.out.println(userDetails);


//            UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPassword(), new ArrayList<>());

            // 如果 JWT Token 有效，则进行身份验证
            if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                System.out.println("验证成功");
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 继续处理请求
        filterChain.doFilter(request, response);
    }
}

