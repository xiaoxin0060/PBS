package com.xiaoxin.blog.web.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaptchaVo {
    private String imageUrl;
    private String key;

}
