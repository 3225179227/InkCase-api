package com.more_sleep.inkcaseapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.more_sleep.inkcaseapi.common.utils.JacksonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
// 不继承WebMvcConfigurationSupport，否则会导致静态资源无法访问
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${me.upload.path}")
    private String uploadPath;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(1,messageConverter);
    }

    // 配置静态资源处理
    @Override
    public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/**").addResourceLocations("file:" + uploadPath);是什么 ？ 为什么要这样配置？

        registry.addResourceHandler("/**").addResourceLocations("file:" + uploadPath);
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
//    @Bean
//    public ResourceHttpRequestHandler resourceHttpRequestHandler() {
//        ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
//        handler.setApplicationContext(applicationContext);
//        handler.setLocations(Arrays.asList(new ClassPathResource("/")));
//        return handler;
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("file:" + uploadPath);
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

    // 配置静态资源处理
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
//                .maxAge(3600 * 24);
//    }
}
