package com.xiaoxin.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 标签表
 * @TableName tag
 */
@TableName(value ="tag")
@Data
public class Tag {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String name;

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