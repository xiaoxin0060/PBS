package com.xiaoxin.blog.web.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "忘记密码发送验证码请求")
public class ForgotPasswordSendCodeDto {

    @Schema(description = "注册邮箱地址",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "user@example.com",
            format = "email")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}