package com.xiaoxin.blog.web.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "验证码响应")
public class CaptchaVo {

    @Schema(description = "验证码图片（Base64编码）",
            example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA...")
    private String captchaImage;

    @Schema(description = "验证码标识key，用于验证时回传",
            example = "auth-captcha-f47ac10b-58cc-4372-a567-0e02b2c3d479")
    private String captchaKey;

    @Schema(description = "验证码类型",
            example = "login")
    private String captchaType;

    @Schema(description = "过期时间（秒）",
            example = "600")
    private Long expireTime;

    @Schema(description = "生成时间",
            example = "2023-08-05 14:30:25")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    // 简化构造函数，匹配你现有的使用方式
    public CaptchaVo(String captchaImage, String captchaKey) {
        this.captchaImage = captchaImage;
        this.captchaKey = captchaKey;
        this.captchaType = "login";
        this.expireTime = 600L;
        this.createTime = new Date();
    }
}