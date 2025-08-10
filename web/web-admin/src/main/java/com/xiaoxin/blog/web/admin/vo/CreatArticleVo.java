package com.xiaoxin.blog.web.admin.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreatArticleVo {
    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotNull(message = "分类不能为空")
    private Long categoryId;

    @NotNull(message = "用户不能为空")
    private Long userId;

    private MultipartFile coverImage;
}
