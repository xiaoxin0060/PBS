package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Notification;
import com.xiaoxin.blog.web.app.dto.NotificationQueryDto;
import com.xiaoxin.blog.web.app.service.NotificationService;
import com.xiaoxin.blog.web.app.mapper.NotificationMapper;
import com.xiaoxin.blog.web.app.vo.NotificationVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小新
* @description 针对表【notification】的数据库操作Service实现
* @createDate 2025-08-15 18:39:48
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{

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




