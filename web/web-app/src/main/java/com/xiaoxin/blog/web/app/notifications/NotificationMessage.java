package com.xiaoxin.blog.web.app.notifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaoxin.blog.model.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(description = "MQ 通知消息体")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private Long receiverId;
    private NotificationType type;
    private String title;
    private String content;
    private String payloadJson;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Builder.Default
    private Date createdAt = new Date();
}