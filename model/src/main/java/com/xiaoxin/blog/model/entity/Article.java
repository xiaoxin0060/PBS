package com.xiaoxin.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 文章表
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String content;

    /**
     * 封面URL（MinIO存储）
     */
    private String coverImage;

    /**
     * 
     */
    private Long categoryId;

    /**
     * 业务状态: 0-草稿,1-已发布
     */
    private Integer status;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 浏览量（MQ异步更新）
     */
    private Integer viewCount;

    /**
     * 点赞数（Redis计数）
     */
    private Integer likeCount;

    /**
     * 逻辑删除: 0-未删,1-已删
     */
    private Integer deleted;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Long createBy;

    /**
     * 
     */
    private Long updateBy;

}