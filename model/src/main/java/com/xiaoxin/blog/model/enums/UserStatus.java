package com.xiaoxin.blog.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    NORMAL(0, "正常"),
    DISABLED(1, "禁用");
    
    private final Integer code;
    private final String description;
}