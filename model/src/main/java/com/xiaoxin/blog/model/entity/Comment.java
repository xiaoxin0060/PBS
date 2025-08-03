package com.xiaoxin.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment {
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
    private Long userId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Long parentId;

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


}