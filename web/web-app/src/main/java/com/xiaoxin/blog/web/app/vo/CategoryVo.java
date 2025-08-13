package com.xiaoxin.blog.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类基础信息VO
 */
@Data
@Schema(description = "分类基础信息")
public class CategoryVo {
    private Long id;
    private String name;
    @Schema(description = "分类下已发布文章数")
    private Long articleCount;
}