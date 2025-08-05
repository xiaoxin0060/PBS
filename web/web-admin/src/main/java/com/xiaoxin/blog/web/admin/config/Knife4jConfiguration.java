package com.xiaoxin.blog.web.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("后台管理系统API")
                        .version("1.0")
                        .description("后台管理系统的接口文档"));
    }
    @Bean
    public GroupedOpenApi userAPI() {
        return GroupedOpenApi.builder().group("用户信息管理").
                pathsToMatch("/admin/user/**").
                build();
    }

    @Bean
    public GroupedOpenApi articleAPI() {
        return GroupedOpenApi.builder().group("文章信息管理").
                pathsToMatch("/admin/article/**").
                build();
    }

    @Bean
    public GroupedOpenApi tagAPI() {
        return GroupedOpenApi.builder().group("标签信息管理").
                pathsToMatch("/admin/tag/**").
                build();
    }
    @Bean
    public GroupedOpenApi commentAPI() {
        return GroupedOpenApi.builder().group("评论信息管理").
                pathsToMatch("/admin/comment/**").
                build();
    }
    @Bean
    public GroupedOpenApi categoryAPI() {
        return GroupedOpenApi.builder().group("分类信息管理").
                pathsToMatch("/admin/category/**").
                build();
    }
    @Bean
    public GroupedOpenApi authAPI() {
        return GroupedOpenApi.builder().group("登录认证管理").
                pathsToMatch("/admin/auth/**").
                build();
    }


}