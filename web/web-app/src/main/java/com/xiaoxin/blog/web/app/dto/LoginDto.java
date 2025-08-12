package com.xiaoxin.blog.web.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "用户登录请求")
public class LoginDto {

    @Schema(description = "用户名或邮箱",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "admin",
            maxLength = 100)
    @NotBlank(message = "用户名不能为空")
    @Size(max = 100, message = "用户名长度不能超过100位")
    private String username;

    @Schema(description = "登录密码",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "123456",
            minLength = 6,
            maxLength = 20)
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20位之间")
    private String password;

    @Schema(description = "图形验证码",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "ABCD")
    @NotBlank(message = "验证码不能为空")
    @Size(min = 4, max = 6, message = "验证码长度不正确")
    private String captcha;

    @Schema(description = "验证码标识key",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "login_1691234567890")
    @NotBlank(message = "验证码key不能为空")
    private String captchaKey;

    @Schema(description = "记住我，延长登录时效",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "false")
    private Boolean rememberMe = false;
}