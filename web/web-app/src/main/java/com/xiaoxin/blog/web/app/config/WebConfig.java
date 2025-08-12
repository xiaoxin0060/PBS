package com.xiaoxin.blog.web.app.config;

import com.xiaoxin.blog.web.app.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/app/**")
                .excludePathPatterns("/app/auth/login","/app/auth/logout","/app/auth/register","/app/auth/getCaptcha");
    }
}