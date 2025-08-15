package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.model.entity.Notification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.web.app.dto.NotificationQueryDto;
import com.xiaoxin.blog.web.app.vo.NotificationVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【notification】的数据库操作Service
* @createDate 2025-08-15 18:39:48
*/
public interface NotificationService extends IService<Notification> {

    IPage<NotificationVo> getNotifications(NotificationQueryDto queryDto);

    Long getUnreadCount();

    void markAsRead(Long id);

    void batchMarkAsRead(List<Long> ids);
}
