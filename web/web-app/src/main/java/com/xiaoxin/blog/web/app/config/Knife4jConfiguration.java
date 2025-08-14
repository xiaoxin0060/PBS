package com.xiaoxin.blog.web.app.config;

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
                        .title("个人博客系统 - APP端API")
                        .version("1.0")
                        .description("个人博客系统用户端的接口文档，包含完整的用户功能模块"));
    }

    @Bean
    public GroupedOpenApi authAPI() {
        return GroupedOpenApi.builder()
                             .group("认证管理")
                             .pathsToMatch("/app/auth/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi contentAPI() {
        return GroupedOpenApi.builder()
                             .group("内容管理")
                             .pathsToMatch("/app/posts/**", "/app/categories/**", "/app/tags/**", "/app/drafts/**", "/app/category-tag/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi discoverAPI() {
        return GroupedOpenApi.builder()
                             .group("发现功能")
                             .pathsToMatch("/app/discover/**", "/app/recommend/**", "/app/search/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi interactionAPI() {
        return GroupedOpenApi.builder()
                             .group("用户交互")
                             .pathsToMatch("/app/comments/**", "/app/favorites/**", "/app/likes/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi userAPI() {
        return GroupedOpenApi.builder()
                             .group("用户中心")
                             .pathsToMatch("/app/users/**", "/app/profile/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi systemAPI() {
        return GroupedOpenApi.builder()
                             .group("系统功能")
                             .pathsToMatch("/app/notifications/**", "/app/reports/**", "/app/statistics/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi uploadAPI() {
        return GroupedOpenApi.builder()
                             .group("文件上传")
                             .pathsToMatch("/app/upload/**")
                             .build();
    }
}