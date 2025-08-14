package com.xiaoxin.blog.web.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentQueryDto{
    private Long articleId;
    private Long userId;
    private Long parentId;
    private Integer pageNum;
    private Integer pageSize;
}
