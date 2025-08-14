package com.xiaoxin.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @TableName click_log
 */
@TableName(value ="click_log")
@Data
public class ClickLog {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    /**
     * 0=ARTICLE,1=USER,2=CATEGORY,3=TAG
     */
    private Integer targetType;
    /**
     *被点击对象的主键ID
     * 当 targetType=0 时，targetId = article.id
     * 当 targetType=1 时，targetId = user.id
     * 当 targetType=2 时，targetId = category.id
     * 当 targetType=3 时，targetId = tag.id
     * */
    private Long targetId;

    private Date createdAt;


}