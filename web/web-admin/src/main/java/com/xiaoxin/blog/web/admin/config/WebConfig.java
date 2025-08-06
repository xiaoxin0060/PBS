package com.xiaoxin.blog.web.admin.config;

import com.xiaoxin.blog.web.admin.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/auth/login","/admin/auth/logout","/admin/auth/register","/admin/auth/getCaptcha");
    }
}
