package com.xiaoxin.blog.web.admin.vo;

import lombok.Data;

@Data
public class TagVo {
    private Long id;
    private Long articleId;
    private String name;
}
