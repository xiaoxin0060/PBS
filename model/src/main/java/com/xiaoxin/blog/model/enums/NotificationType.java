package com.xiaoxin.blog.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
    SYSTEM(0, "系统"),
    COMMENT(1, "评论"),
    LIKE(2, "点赞"),
    MESSAGE(3, "私信");

    private final int code;
    private final String label;

    public static NotificationType fromCode(int code) {
        for (NotificationType t : values()) if (t.code == code) return t;
        throw new IllegalArgumentException("Invalid NotificationType code: " + code);
    }
}