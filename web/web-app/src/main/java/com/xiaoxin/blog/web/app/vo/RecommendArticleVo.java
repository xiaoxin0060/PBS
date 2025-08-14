package com.xiaoxin.blog.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class RecommendArticleVo{
    @Schema(description = "文章ID", example = "1001")
    private Long id;

    @Schema(description = "文章标题", example = "Spring Boot 入门指南")
    private String title;

    @Schema(description = "作者用户名", example = "xiaoming")
    private String username;

    @Schema(description = "创建时间", example = "2025-06-15T10:30:00")
    private Date createTime;
}
