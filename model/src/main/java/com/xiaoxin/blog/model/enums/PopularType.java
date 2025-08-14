package com.xiaoxin.blog.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PopularType {
    HOT("最热门", "view_count"),           // 按浏览量排序
    LIKED("最受欢迎", "like_count"),       // 按点赞数排序
    COMMENTED("讨论最多", "comment_count"), // 按评论数排序
    TAGGED("标签最多", "tag_count");       // 按标签数量排序

    private final String description;
    private final String orderField;


}