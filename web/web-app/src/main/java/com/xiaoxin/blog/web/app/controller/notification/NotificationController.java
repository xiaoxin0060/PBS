package com.xiaoxin.blog.web.app.controller.notification;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "消息通知")
@RequestMapping("/app/notifications")
@RestController
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;
    
    @Operation(summary = "获取通知列表")
    @GetMapping
    public Result<PageResult<NotificationVo>> getNotifications(NotificationQueryDto queryDto) {
        PageResult<NotificationVo> notifications = notificationService.getNotifications(queryDto);
        return Result.ok(notifications);
    }
    
    @Operation(summary = "获取未读数量")
    @GetMapping("/unread-count")
    public Result<Long> getUnreadCount() {
        Long count = notificationService.getUnreadCount();
        return Result.ok(count);
    }
    
    @Operation(summary = "标记已读")
    @PutMapping("/{id}/read")
    public Result<Void> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return Result.ok();
    }
    
    @Operation(summary = "批量标记已读")
    @PutMapping("/mark-read")
    public Result<Void> batchMarkAsRead(@RequestBody(required = false) List<Long> ids) {
        notificationService.batchMarkAsRead(ids);
        return Result.ok();
    }
}