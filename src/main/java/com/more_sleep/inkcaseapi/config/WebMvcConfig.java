package com.more_sleep.inkcaseapi.config;

import com.more_sleep.inkcaseapi.common.utils.JacksonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${me.upload.path}")
    private String uploadPath;

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0,messageConverter);
    }

//    @Bean
//    public ResourceHttpRequestHandler resourceHttpRequestHandler() {
//        ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
//        handler.setApplicationContext(applicationContext);
//        handler.setLocations(Arrays.asList(new ClassPathResource("/")));
//        return handler;
//    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:" + uploadPath);
    }

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
