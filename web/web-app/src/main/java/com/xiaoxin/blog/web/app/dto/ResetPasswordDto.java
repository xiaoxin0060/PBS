package com.xiaoxin.blog.web.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "重置密码请求")
public class ResetPasswordDto {

    @Schema(description = "注册邮箱地址",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "user@example.com",
            format = "email")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "邮箱验证码",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "123456",
            minLength = 6,
            maxLength = 6)
    @NotBlank(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "验证码必须为6位")
    private String verificationCode;

    @Schema(description = "新密码，6-20位包含字母和数字",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "newpass123",
            minLength = 6,
            maxLength = 20)
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20位之间")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[\\s\\S]{6,20}$", message = "密码必须包含字母和数字")
    private String newPassword;

    @Schema(description = "确认新密码",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "newpass123")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @AssertTrue(message = "两次输入的密码不一致")
    @Schema(hidden = true)
    public boolean isPasswordMatching() {
        return newPassword != null && newPassword.equals(confirmPassword);
    }
}
