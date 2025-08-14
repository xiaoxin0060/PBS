package com.xiaoxin.blog.web.app.vo;

import lombok.Data;

@Data
public class UserProfileVo{
    private Long id;
    private String username;
    private String email;
    private String avatar;
    private Integer role;
}
