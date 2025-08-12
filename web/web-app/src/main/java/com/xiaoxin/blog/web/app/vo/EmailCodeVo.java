package com.xiaoxin.blog.web.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "邮箱验证码发送响应")
public class EmailCodeVo {

    @Schema(description = "发送是否成功",
            example = "true")
    private Boolean success;

    @Schema(description = "响应消息",
            example = "验证码已发送到您的邮箱，请查收")
    private String message;

    @Schema(description = "验证码有效期（分钟）",
            example = "5")
    private Integer expireMinutes;

    @Schema(description = "下次可发送间隔时间（秒）",
            example = "60")
    private Integer nextSendInterval;

    @Schema(description = "发送时间",
            example = "2023-08-05 14:30:25")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;
}