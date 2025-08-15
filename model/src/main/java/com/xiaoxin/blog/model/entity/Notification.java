package com.xiaoxin.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @TableName notification
 */
@TableName(value ="notification")
@Data
public class Notification {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long receiverId;

    private Integer type;

    private String title;

    private String content;

    private String payloadJson;

    private Integer isRead;

    private Date createdAt;

    private Date readAt;

}