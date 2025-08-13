package com.xiaoxin.blog.web.app.dto;

import lombok.Data;

@Data
public class UpdateDraftDto{

    private String title;

    private String content;

    private Long categoryId;
}
