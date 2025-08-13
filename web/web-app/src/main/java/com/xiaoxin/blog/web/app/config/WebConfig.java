package com.xiaoxin.blog.web.app.config;

import com.xiaoxin.blog.web.app.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    private final MyInterceptor myInterceptor;
    public WebConfig(MyInterceptor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/app/**")
                .excludePathPatterns("/app/auth/**","/app/categories/**","/app/tags/**");
    }
}