package com.xiaoxin.blog.web.app.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AddCommentDto{
    private Long articleId;
    private Long userId;
    private Long parentId;
    private String content;
    private Date createTime;
}
