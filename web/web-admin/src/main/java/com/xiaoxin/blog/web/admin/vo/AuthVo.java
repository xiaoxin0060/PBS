package com.xiaoxin.blog.web.admin.vo;

import lombok.Data;

@Data
public class AuthVo {
    private String username;

    private String password;

    private String captchaKey;

    private String captchaCode;
}
