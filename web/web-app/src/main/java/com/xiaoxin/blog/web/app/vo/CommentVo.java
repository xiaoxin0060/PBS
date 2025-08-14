package com.xiaoxin.blog.web.app.vo;

import lombok.Data;

import java.util.Date;
@Data
public class CommentVo{
    private Long id;
    private String userName;
    private String content;
    private Date createTime;
}
