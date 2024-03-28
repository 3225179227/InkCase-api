package com.more_sleep.inkcaseapi.common.config;

import com.more_sleep.inkcaseapi.common.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * WebSecurity配置
 *
 * @Author: lbj
 * @Date: 2024/3/24
 */
@Configuration
@EnableWebSecurity// springBoot可免去这个配置
public class WebSecurityConfig {
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
                        .successHandler(new AAuthenticationSuccessHandler())
                        .failureHandler(new AAuthenticationFailureHandler())
        );
        // 退出登录处理器
        http.logout(logout -> logout
                .logoutSuccessHandler(new ALogoutSuccessHandler())
        );
        // 请求未授权处理器
        http.exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(new AAuthenticationEntryPoint());
                    exceptionHandling.accessDeniedHandler(new AAccessDeniedHandler());
                }
        );
        http.sessionManagement(session -> session.maximumSessions(1).expiredSessionStrategy(new ASessionInformationExpiredStrategy()));


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

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of("*")); // 设置允许访问的域名
//        config.setAllowedMethods(List.of("*")); // 允许所有HTTP方法
//        config.setAllowedHeaders(List.of("*")); // 允许所有请求头
//        config.setAllowCredentials(true); // 允许发送凭据（例如Cookie）
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

    // 跨域请求配置
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8080")); // 允许所有源
        configuration.setAllowedMethods(List.of("*")); // 允许所有方法
        configuration.setAllowedHeaders(List.of("*")); // 允许所有头部
        configuration.setAllowCredentials(true); // 允许发送凭证

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // 密码编码器
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}
