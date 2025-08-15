package com.xiaoxin.blog.web.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaoxin.blog.model.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(description = "通知展示对象")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationVo {
    @Schema(description = "通知ID")
    private Long id;

    @Schema(description = "接收者用户ID")
    private Long receiverId;

    @Schema(description = "通知类型")
    private NotificationType type;

    @Schema(description = "标题（可选）")
    private String title;

    @Schema(description = "简要内容/文案（可选）")
    private String content;

    @Schema(description = "扩展载荷（JSON 字符串，前端可按 type 解析）")
    private String payloadJson;

    @Schema(description = "是否已读")
    private Boolean isRead;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Schema(description = "阅读时间（已读时存在）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date readAt;
}