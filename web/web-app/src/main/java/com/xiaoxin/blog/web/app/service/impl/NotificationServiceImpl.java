package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.NotificationQueryDto;
import com.xiaoxin.blog.web.app.service.NotificationService;
import com.xiaoxin.blog.web.app.vo.NotificationVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Override
    public IPage<NotificationVo> getNotifications(NotificationQueryDto queryDto)
    {
        return null;
    }

    @Override
    public Long getUnreadCount()
    {
        return 0L;
    }

    @Override
    public void markAsRead(Long id)
    {

    }

    @Override
    public void batchMarkAsRead(List<Long> ids)
    {

    }
}
