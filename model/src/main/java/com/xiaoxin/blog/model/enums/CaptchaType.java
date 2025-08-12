package com.xiaoxin.blog.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CaptchaType {
    
    LOGIN("login", "登录验证码"),
    
    REGISTER("register", "注册验证码"),
    
    RESET_PASSWORD("reset_password", "重置密码验证码"),
    
    CHANGE_EMAIL("change_email", "修改邮箱验证码");
    
    private final String code;
    private final String description;
}