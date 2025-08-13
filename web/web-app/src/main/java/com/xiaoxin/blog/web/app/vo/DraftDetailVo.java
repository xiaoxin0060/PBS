package com.xiaoxin.blog.web.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DraftDetailVo{
    private Long id;

    private String userName;

    private String title;

    private String content;

    private Long categoryId;
}
