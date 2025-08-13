package com.xiaoxin.blog.web.app.vo;

import lombok.Data;

@Data
public class DraftVo{
    private Long id;

    private String userName;

    private String title;

    private Long categoryId;
}
