package com.xiaoxin.blog.web.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "用户注册请求")
public class RegisterDto {

    @Schema(description = "用户名，3-20位字母数字下划线",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "testuser",
            pattern = "^[a-zA-Z0-9_]+$",
            minLength = 3,
            maxLength = 20)
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3-20位之间")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "用户名只能包含字母、数字和下划线")
    private String username;

    @Schema(description = "邮箱地址",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "test@example.com",
            format = "email",
            maxLength = 100)
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100位")
    private String email;

    @Schema(description = "密码，6-20位包含字母和数字",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "pass123",
            minLength = 6,
            maxLength = 20)
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20位之间")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[\\s\\S]{6,20}$", message = "密码必须包含字母和数字")
    private String password;

    @Schema(description = "确认密码",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "pass123")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @Schema(description = "图形验证码",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1234",
            minLength = 4,
            maxLength = 6)
    @NotBlank(message = "验证码不能为空")
    @Size(min = 4, max = 6, message = "验证码长度不正确")
    private String captcha;

    @Schema(description = "验证码标识key",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "register_1691234567890")
    @NotBlank(message = "验证码key不能为空")
    private String captchaKey;

    // 自定义校验：确认密码一致性
    @AssertTrue(message = "两次输入的密码不一致")
    @Schema(hidden = true) // 隐藏该字段，不在文档中显示
    public boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword);
    }
}
