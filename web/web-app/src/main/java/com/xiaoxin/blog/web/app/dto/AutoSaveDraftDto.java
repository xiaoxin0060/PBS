package com.xiaoxin.blog.web.app.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AutoSaveDraftDto{
    private Long id;

    private String title;

    private String content;

    private String coverImage;

    private Long categoryId;

    private Date lastUpdateTime;
}
