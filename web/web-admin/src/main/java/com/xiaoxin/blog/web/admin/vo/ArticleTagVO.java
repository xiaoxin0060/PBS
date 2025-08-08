package com.xiaoxin.blog.web.admin.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ArticleTagVO {
    @NotNull(message = "文章ID不能为空")
    @Min(value = 1, message = "文章ID必须大于0")
    private Long articleId;
    
    @NotEmpty(message = "标签ID列表不能为空")
    private List<Long> tagIds;
}