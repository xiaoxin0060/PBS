package com.xiaoxin.blog.web.app.vo;

import lombok.Data;
@Data
public class PopularArticleVo{
    private Long id;
    private String title;
    private Integer viewCount;
    private Integer likeCount;
    private Long commentCount;
}
