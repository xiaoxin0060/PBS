package com.xiaoxin.blog.web.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Token刷新响应")
public class TokenVo {

    @Schema(description = "新的访问令牌",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "新的刷新令牌",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;

    @Schema(description = "令牌类型",
            example = "Bearer")
    private String tokenType = "Bearer";

    @Schema(description = "新令牌过期时间（秒）",
            example = "7200")
    private Long expiresIn;

    @Schema(description = "刷新时间",
            example = "2023-08-05 16:30:25")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date refreshTime;
}