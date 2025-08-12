package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.NotificationQueryDto;
import com.xiaoxin.blog.web.app.vo.NotificationVo;

import java.util.List;

public interface NotificationService{
    IPage<NotificationVo> getNotifications(NotificationQueryDto queryDto);

    Long getUnreadCount();

    void markAsRead(Long id);

    void batchMarkAsRead(List<Long> ids);
}
