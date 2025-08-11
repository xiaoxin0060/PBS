package com.xiaoxin.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文章标签关联表
 *
 * @TableName article_tag
 */
@TableName(value = "article_tag")
@Data
public class ArticleTag extends BaseEntity{
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Long articleId;

    /**
     *
     */
    private Long tagId;

    public ArticleTag(Long articleId, Long tagId){
        this.articleId = articleId;
        this.tagId = tagId;
    }


}