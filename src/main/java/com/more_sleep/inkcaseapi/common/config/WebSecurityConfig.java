package com.more_sleep.inkcaseapi.common.config;

import com.more_sleep.inkcaseapi.common.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;

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
        http.cors(withDefaults());

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

}
