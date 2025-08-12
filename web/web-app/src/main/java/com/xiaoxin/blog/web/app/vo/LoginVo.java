package com.xiaoxin.blog.web.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Schema(description = "登录成功响应")
public class LoginVo {

    @Schema(description = "访问令牌",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "刷新令牌",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;

    @Schema(description = "令牌类型",
            example = "Bearer")
    private String tokenType = "Bearer";

    @Schema(description = "令牌过期时间（秒）",
            example = "7200")
    private Long expiresIn;

    @Schema(description = "用户基本信息")
    private UserInfoVo userInfo;

    @Schema(description = "登录时间",
            example = "2023-08-05 14:30:25")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    // 内部静态类：用户基本信息
    @Data
    @Schema(description = "用户基本信息")
    public static class UserInfoVo {

        @Schema(description = "用户ID", example = "1001")
        private Long id;

        @Schema(description = "用户名", example = "admin")
        private String username;

        @Schema(description = "邮箱地址", example = "admin@blog.com")
        private String email;

        @Schema(description = "头像URL")
        private String avatar;

        @Schema(description = "用户角色代码", allowableValues = {"0", "1"})
        private Integer role;

        @Schema(description = "角色名称", allowableValues = {"普通用户", "管理员"})
        private String roleName;

        @Schema(description = "账户状态", allowableValues = {"0", "1"})
        private Integer status;

        @Schema(description = "注册时间", example = "2023-01-15 10:20:30")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        // 保持Date类型，避免转换
        private Date createTime;
    }
}