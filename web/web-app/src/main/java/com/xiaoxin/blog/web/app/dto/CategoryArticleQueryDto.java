package com.xiaoxin.blog.web.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

/**
 * 文章查询DTO
 */
@Data
@Schema(description = "文章查询参数")
@Builder
public class CategoryArticleQueryDto{
    @Schema(description = "分类ID（服务层会强制设置）")
    private Long categoryId;

    @Schema(description = "文章状态：0 草稿，1 “发布”")
    private Integer status;

    @Schema(description = "页码，从 1 开始，默认 1")
    @Min(1)
    private Integer pageNum = 1;

    @Schema(description = "每页大小，默认 10，最大 100")
    @Min(1) @Max(100)
    private Integer pageSize = 10;
}