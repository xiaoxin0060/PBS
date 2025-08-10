package com.xiaoxin.blog.web.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {
    private Long id;
    private String title;
    private Long userId;
    private String coverImage;
    private CategoryVo category;
    private List<TagVo> tags;
    private Integer status;
}
