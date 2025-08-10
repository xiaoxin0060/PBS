package com.xiaoxin.blog.web.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleQueryVo {
    private String title;
    private Long userId;
    private Long categoryId;
    private List<Long> tagId;
}
