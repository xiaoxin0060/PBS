package com.xiaoxin.blog.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 文章列表VO
 */
@Data
@Schema(description = "文章列表信息")
public class ArticleListVo {
    private Long id;
    private String title;
    private String coverImage;
    private String categoryName;
    private List<String> tagsName;
    private Integer isTop;
    private Integer viewCount;
    private Integer likeCount;
    private Date createTime;


}