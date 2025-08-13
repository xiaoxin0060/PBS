package com.xiaoxin.blog.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 标签信息VO
 */
@Data
@Schema(description = "标签信息")
public class TagVo {

    @Schema(description = "标签ID")
    private Long id;

    @Schema(description = "标签名称")
    private String name;

    @Schema(description = "文章数量")
    private Integer articleCount;
}