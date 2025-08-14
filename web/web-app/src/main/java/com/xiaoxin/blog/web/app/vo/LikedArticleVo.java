package com.xiaoxin.blog.web.app.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LikedArticleVo {
    private Long id;
    private String title;
    private Integer viewCount;
    private Integer likeCount;
    private Date createTime; // 文章创建时间
    private Date likedTime; // 我点赞的时间（来自 article_like.created_time）
}