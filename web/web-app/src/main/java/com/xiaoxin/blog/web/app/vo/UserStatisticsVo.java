package com.xiaoxin.blog.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserStatisticsVo{
    @Schema(description = "文章数量")
    private Long articleCount;
    @Schema(description = "收到的评论数量")
    private Long commentCount;
    @Schema(description = "收获点赞数量")
    private Long likeCount;
}
