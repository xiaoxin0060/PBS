package com.xiaoxin.blog.web.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeStatusVo {
    // 是否已点赞
    private boolean liked;
    // 文章当前点赞数
    private Integer likeCount;
}