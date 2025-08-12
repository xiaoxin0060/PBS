package com.xiaoxin.blog.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    
    USER(0, "普通用户"),
    
    ADMIN(1, "管理员");
    
    private final Integer code;
    private final String description;
    
    public static String getRoleName(Integer role) {
        for (UserRole userRole : values()) {
            if (userRole.getCode().equals(role)) {
                return userRole.getDescription();
            }
        }
        return "未知角色";
    }
}