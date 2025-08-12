package com.xiaoxin.blog.web.app.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleQueryDto {

    private Long categoryId;
    private List<Long> tagIds;
    private Integer page;
    private Integer size;
    private String sort;
}