package com.xiaoxin.blog.web.app.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ArticleDetailVo{
    private Long id;
    private String title;
    private String content;
    private List<String> tagsName;
    private Integer isTop;
    private Integer viewCount;
    private Integer likeCount;
    private Date createTime;
}
