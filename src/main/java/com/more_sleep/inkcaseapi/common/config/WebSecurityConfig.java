package com.more_sleep.inkcaseapi.common.config;

import com.alibaba.fastjson.JSON;
import com.more_sleep.inkcaseapi.common.Code;
import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.common.handler.*;
import com.more_sleep.inkcaseapi.common.utils.JwtUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * WebSecurity配置
 *
 * @Author: lbj
 * @Date: 2024/3/24
 */
@Configuration
@EnableWebSecurity// springBoot可免去这个配置
@AllArgsConstructor
public class WebSecurityConfig {
    private final JwtUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    private final ApplicationContext applicationContext;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 配置请求授权
        http.authorizeRequests(
                authorizeRequests -> authorizeRequests
                        /*.requestMatchers("GET", "/user")
                        .hasAuthority("USER_LIST")
                        .requestMatchers("POST", "/user/add")
                        .hasAuthority("USER_ADD")*/

                        // 开放注册路径
                        .requestMatchers("/user/register")
                        .permitAll()

                        // 开放邮箱路径
                        .requestMatchers("/mail/sendmail")
                        .permitAll()

                        // 开放文章路径
                        .requestMatchers("/article/**")
                        .permitAll()

                        // 发布文章需要USER权限
                        .requestMatchers("/article/publish")
                        .hasRole("USER")

                        //  对于/user/**的请求需要ADMIN权限
                        .requestMatchers("/manage/**")
                        .hasRole("ADMIN")

                        // 对于所有请求都需要认证
                        .anyRequest()
                        // 已认证的请求不会被自动授权
                        .authenticated()
        );
//                .formLogin(withDefaults());
        // 登录处理器
        http.formLogin(
                // 自定义登录页面
                formLogin -> formLogin
                        .loginPage("/login")
                        // 无需登录认证
                        .permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        // 登录成功处理器
                        .successHandler((request, response, authentication)->{
                            response.setContentType("application/json;charset=UTF-8");

                            // 生成JWT，并放置到请求头中
                            String jwt = jwtUtils.generateToken(authentication.getName());
                            response.setHeader(jwtUtils.getHeader(), jwt);

                            String json = JSON.toJSONString(R.success(Map.of("Oauth-Token", jwt), "登录成功"));
                            response.setContentType("application/json;charset=utf-8");
                            response.getWriter().write(json);
                        })
                        .failureHandler(new AAuthenticationFailureHandler())
        );
        // 退出登录处理器
        http.logout(logout -> logout
                .logoutSuccessHandler((request, response, authentication)->{
                    String json = JSON.toJSONString(R.success("注销成功"));
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(json);
                })
        );
        // 请求未授权处理器
        http.exceptionHandling(exceptionHandling -> {
                    // 未登录时的处理
                    exceptionHandling.authenticationEntryPoint((request, response, authException)->{
                        String json = JSON.toJSONString(R.error(Code.USER_NOT_LOGGED_IN));
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().write(json);
                    });

                    // 权限不足时的处理
                    exceptionHandling.accessDeniedHandler((request, response, accessDeniedException) -> {
                        String json = JSON.toJSONString(R.error(Code.PERMISSION_NO_ACCESS.getCode(), "权限不足"));
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().write(json);
                    });
                }
        );
        // 会话信息过期策略
        http.sessionManagement(session -> session.maximumSessions(1).expiredSessionStrategy((event)->{
            String json = JSON.toJSONString(R.error("该账号已在其他设备登录"));
            HttpServletResponse response = event.getResponse();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        }));


        // 处理跨域
        // 这样是开放所有的跨域请求，生产环境请根据需求配置
        // 应该配置允许的域名
//        http.cors(withDefaults());
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // 关闭csrf
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }


    /*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("admin").password("admin").roles("USER").build());
        return manager;
    }*/


    /*@Bean
    public UserDetailsService userDetailsService() {
        DBUserDetailsManager manager = new DBUserDetailsManager();
        return manager;
    }*/

    // 明文密码编码器
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    /*@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // 设置允许访问的域名
        config.setAllowedMethods(List.of("*")); // 允许所有HTTP方法
        config.setAllowedHeaders(List.of("*")); // 允许所有请求头
        config.setAllowCredentials(true); // 允许发送凭据（例如Cookie）
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }*/

    // 跨域请求配置
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("localhost:8080")); // 配置可访问路径
        configuration.setAllowedOriginPatterns(List.of("*"));// 允许所有源
        configuration.setAllowedMethods(List.of("*")); // 允许所有方法
        configuration.setAllowedHeaders(List.of("*")); // 允许所有头部
        configuration.setAllowCredentials(true); // 允许发送凭证

        // 这个配置是干什么的？ 为什么要配置这个？
        // 为了让Spring Security的跨域配置生效
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        ObjectPostProcessor<Object> opp = applicationContext.getBean(ObjectPostProcessor.class);
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(opp);
        builder.userDetailsService(userDetailsService);
        return builder.build();
    }


}
