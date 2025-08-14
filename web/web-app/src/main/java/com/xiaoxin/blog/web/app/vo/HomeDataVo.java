package com.xiaoxin.blog.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class HomeDataVo{
    @Schema(description = "热门文章")
    private List<PopularArticleVo> popularArticles;
    @Schema(description = "热门标签")
    private List<TagVo> popularTags;
    @Schema(description = "热门分类")
    private List<CategoryVo> popularCategories;
}
