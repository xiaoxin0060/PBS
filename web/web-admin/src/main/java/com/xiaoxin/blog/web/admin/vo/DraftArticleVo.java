package com.xiaoxin.blog.web.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DraftArticleVo {
    private Long id;
    private String title;
    private Long userId;
    private String coverImage;
}
