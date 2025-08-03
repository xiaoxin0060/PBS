package com.xiaoxin.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String email;

    /**
     * 头像URL（MinIO存储）
     */
    private String avatar;

    /**
     * 0-普通用户，1-管理员
     */
    private Integer role;

    /**
     * 0-正常,1-禁用
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 更新人ID
     */
    private Long updateBy;

}