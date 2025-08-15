package com.xiaoxin.blog.web.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaoxin.blog.model.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Schema(description = "通知查询入参")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationQueryDto {
    @Schema(description = "页码，从1开始", example = "1")
    @Builder.Default
    private Integer pageNo = 1;

    @Schema(description = "每页大小", example = "10")
    @Builder.Default
    private Integer pageSize = 10;

    @Schema(description = "按是否已读过滤（null 不限）")
    private Boolean isRead;

    @Schema(description = "按通知类型过滤（可多选）")
    private List<NotificationType> types;

    @Schema(description = "开始时间（含）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @Schema(description = "结束时间（含）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
