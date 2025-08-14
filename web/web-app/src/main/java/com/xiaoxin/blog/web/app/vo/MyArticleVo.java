package com.xiaoxin.blog.web.app.vo;

import lombok.Data;

import java.util.Date;
@Data
public class MyArticleVo{
    private Long id;
    private String userName;
    private String title;
    private Integer viewCount;
    private Integer likeCount;
    private Date createTime;
}
