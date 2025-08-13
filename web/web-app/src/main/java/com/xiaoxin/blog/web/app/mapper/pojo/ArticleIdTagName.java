package com.xiaoxin.blog.web.app.mapper.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleIdTagName {
    private Long articleId;
    private String tagName;
}