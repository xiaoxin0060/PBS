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
                pathsToMatch("/admin/articles/**").
                build();
    }

    @Bean
    public GroupedOpenApi tagAPI() {
        return GroupedOpenApi.builder().group("标签信息管理").
                pathsToMatch("/admin/tags/**").
                build();
    }
    @Bean
    public GroupedOpenApi commentAPI() {
        return GroupedOpenApi.builder().group("评论信息管理").
                pathsToMatch("/admin/comments/**").
                build();
    }
    @Bean
    public GroupedOpenApi categoryAPI() {
        return GroupedOpenApi.builder().group("分类信息管理").
                pathsToMatch("/admin/categories/**").
                build();
    }
    @Bean
    public GroupedOpenApi authAPI() {
        return GroupedOpenApi.builder().group("登录认证管理").
                pathsToMatch("/admin/auth/**").
                build();
    }
    @Bean
    public GroupedOpenApi fileAPI() {
        return GroupedOpenApi.builder().group("文件管理").
                pathsToMatch("/admin/file/**").
                build();
    }
    @Bean
    public GroupedOpenApi articleTagAPI() {
        return GroupedOpenApi.builder().group("文章标签关系管理").
                pathsToMatch("/admin/articleTag/**").
                                     build();
    }


}