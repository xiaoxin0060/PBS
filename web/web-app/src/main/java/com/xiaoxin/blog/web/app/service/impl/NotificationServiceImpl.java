package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.model.entity.Notification;
import com.xiaoxin.blog.model.enums.NotificationType;
import com.xiaoxin.blog.web.app.dto.NotificationQueryDto;
import com.xiaoxin.blog.web.app.mapper.NotificationMapper;
import com.xiaoxin.blog.web.app.service.NotificationService;
import com.xiaoxin.blog.web.app.vo.NotificationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 小新
* @description 针对表【notification】的数据库操作Service实现
* @createDate 2025-08-15 18:39:48
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public IPage<NotificationVo> getNotifications(NotificationQueryDto queryDto)
    {
        Long userId = LoginUserHolder.get().getUserId();
        IPage<Notification> page = new Page<>(queryDto.getPageNo(), queryDto.getPageSize());
        LambdaQueryWrapper<Notification> qw = new LambdaQueryWrapper<Notification>()
                .eq(Notification::getReceiverId, userId)
                .orderByDesc(Notification::getCreatedAt);

        if (queryDto.getIsRead() != null) {
            qw.eq(Notification::getIsRead, Boolean.TRUE.equals(queryDto.getIsRead()) ? 1 : 0);
        }
        if (!CollectionUtils.isEmpty(queryDto.getTypes())) {
            List<Integer> typeCodes = queryDto.getTypes().stream()
                                              .map(NotificationType::getCode)
                                              .collect(Collectors.toList());
            qw.in(Notification::getType, typeCodes);
        }
        Date begin = queryDto.getBeginTime();
        Date end = queryDto.getEndTime();
        if (begin != null) {
            qw.ge(Notification::getCreatedAt, begin);
        }
        if (end != null) {
            qw.le(Notification::getCreatedAt, end);
        }
        IPage<Notification> notificationIPage = notificationMapper.selectPage(page, qw);
        IPage<NotificationVo> voPage  = new Page<>(notificationIPage.getCurrent(), notificationIPage.getSize(),notificationIPage.getTotal());
        List<NotificationVo> voRecoeds = notificationIPage.getRecords().stream().map(NotificationServiceImpl::toVo)
                                                        .collect(Collectors.toList());
        voPage.setRecords(voRecoeds);
        return voPage;

    }

    @Override
    public Long getUnreadCount() {
        Long userId = LoginUserHolder.get().getUserId();
        LambdaQueryWrapper<Notification> qw = new LambdaQueryWrapper<Notification>()
                .eq(Notification::getReceiverId, userId)
                .eq(Notification::getIsRead, 0);
        // MyBatis-Plus selectCount 返回 Long
        return notificationMapper.selectCount(qw);
    }

    @Override
    public void markAsRead(Long id) {
        if (id == null) return;
        Long userId = LoginUserHolder.get().getUserId();

        // 只更新属于当前用户的该记录，避免越权
        LambdaUpdateWrapper<Notification> uw = new LambdaUpdateWrapper<Notification>()
                .eq(Notification::getId, id)
                .eq(Notification::getReceiverId, userId)
                .set(Notification::getIsRead, 1)
                .set(Notification::getReadAt, new Date());

        notificationMapper.update(uw);
    }

    @Override
    public void batchMarkAsRead(List<Long> ids) {
        Long userId = LoginUserHolder.get().getUserId();

        LambdaUpdateWrapper<Notification> uw = new LambdaUpdateWrapper<Notification>()
                .eq(Notification::getReceiverId, userId)
                .eq(Notification::getIsRead, 0)
                .set(Notification::getIsRead, 1)
                .set(Notification::getReadAt, new Date());

        // 如果传了 ids，则只更新这些 id；如果没传或为空，则将该用户所有未读标记为已读
        if (!CollectionUtils.isEmpty(ids)) {
            uw.in(Notification::getId, ids);
        }

        notificationMapper.update(uw);
    }
    // Entity -> VO 转换
    private static NotificationVo toVo(Notification e) {
        return NotificationVo.builder()
                             .id(e.getId())
                             .receiverId(e.getReceiverId())
                             .type(e.getType() == null ? null : NotificationType.fromCode(e.getType()))
                             .title(e.getTitle())
                             .content(e.getContent())
                             .payloadJson(e.getPayloadJson())
                             .isRead(e.getIsRead() != null && e.getIsRead() == 1)
                             .createdAt(e.getCreatedAt())
                             .readAt(e.getReadAt())
                             .build();
    }
}




