package com.xiaoxin.blog.web.admin.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UpdateArticleVo {
    private String title;

    private String content;

    private Long categoryId;

    private Long userId;

    private MultipartFile coverImage;
}
