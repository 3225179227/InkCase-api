package com.more_sleep.inkcaseapi.config;

import com.alibaba.fastjson.JSON;
import com.more_sleep.inkcaseapi.common.Code;
import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.common.handler.AAuthenticationFailureHandler;
import com.more_sleep.inkcaseapi.common.utils.JwtUtils;
import com.more_sleep.inkcaseapi.filter.JwtAthFilter;
import com.more_sleep.inkcaseapi.service.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Map;


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

    private final JwtAthFilter jwtAthFilter;
    private final IUserService userService;
    private final JwtUtils jwtUtils;
    private final DBUserDetailsManager userDetailsService;

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
                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/user/user-info").permitAll()

                        // 开放邮箱路径
                        .requestMatchers("/mail/sendmail").permitAll()

                        // 开放文章路径
                        .requestMatchers("/article/**").permitAll()
                        .requestMatchers("/category/**").permitAll()

                        // 发布文章需要USER或ADMIN权限
                        .requestMatchers("/article/publish").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/inkCase-api/article/publish").hasAnyRole("USER", "ADMIN")

                        // tag路径需要USER权限
                        .requestMatchers("/tag/**").hasAnyRole("USER", "ADMIN")

                        //  对于/user/**的请求需要ADMIN权限
                        .requestMatchers("/manage/**").hasRole("ADMIN")

                        // 对于所有请求都需要认证已认证的请求不会被自动授权
                        .anyRequest().authenticated()
        );
//                .formLogin(withDefaults());
        // 登录处理器
        http.formLogin(
                // 自定义登录页面
                formLogin -> formLogin
                        .loginPage("/login")
                        // 无需登录认证
                        .permitAll()
                        // 登录成功处理器
                        .successHandler((request, response, authentication)->{
                            response.setContentType("application/json;charset=UTF-8");

                            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                            // 用户角色
//                            Map<String, Object> claims = new HashMap<>();
//                            List<String> roles = new ArrayList<>();
//                            userDetails.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));
//                            claims.put("roles", roles);

                            String json = JSON.toJSONString(R.success(Map.of("Oauth-Token",jwtUtils.generateToken(userDetails)),"登录成功"));
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

        // OAuth2.0
        // OAuth2.0什么情况下登录成功？ 需要token吗？
        // OAuth2.0登录成功后，会返回一个token，这个token是用来验证用户身份的
        // 如何返回token？ 通过successHandler

        /*http.oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/login")
                .successHandler((request, response, authentication)->{
                    response.setContentType("application/json;charset=UTF-8");
                    String json = JSON.toJSONString(R.success("登录成功"));
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(json);
                })
                .failureHandler(new AAuthenticationFailureHandler())
        );

        http.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                .jwt(jwt -> jwt
                        .jwkSetUri("http://localhost:8080/.well-known/jwks.json")
                        .decoder(JwtDecoders.fromIssuerLocation("http://localhost:8080/oauth/token"))
                ));*/



        http
                // 配置登录表单
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAthFilter, UsernamePasswordAuthenticationFilter.class);


        // 处理跨域
        // 这样是开放所有的跨域请求，生产环境请根据需求配置
        // 应该配置允许的域名
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // 关闭csrf
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    // 配置用户信息服务
    // 通过数据库查询用户信息
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userService::loadUserByUsername;
//    }

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


    // 跨域请求配置
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("localhost:8080")); // 配置可访问路径
        configuration.setAllowedOriginPatterns(List.of("*"));// 允许所有源
        configuration.setAllowedMethods(List.of("*")); // 允许所有方法
        configuration.setAllowedHeaders(List.of("*")); // 允许所有头部
        configuration.setAllowCredentials(true); // 允许发送凭证

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
