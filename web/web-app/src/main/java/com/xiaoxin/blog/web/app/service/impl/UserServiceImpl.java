package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.app.dto.ChangePasswordDto;
import com.xiaoxin.blog.web.app.dto.UpdateProfileDto;
import com.xiaoxin.blog.web.app.mapper.UserMapper;
import com.xiaoxin.blog.web.app.service.UserService;
import com.xiaoxin.blog.web.app.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 小新
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public IPage<UserPageVo> getUserPage(Long userId)
    {
        return null;
    }

    @Override
    public IPage<UserArticleVo> getUserArticles(Long userId, Integer page, Integer size)
    {
        return null;
    }

    @Override
    public IPage<UserActivityVo> getUserActivities(Long userId, Integer page, Integer size)
    {
        return null;
    }

    @Override
    public IPage<UserSearchVo> searchUsers(String keyword, Integer page, Integer size)
    {
        return null;
    }

    @Override
    public UserProfileVo getCurrentUserProfile()
    {
        return null;
    }

    @Override
    public void updateProfile(UpdateProfileDto updateDto)
    {

    }

    @Override
    public String uploadAvatar(MultipartFile file)
    {
        return "";
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto)
    {

    }

    @Override
    public UserStatisticsVo getUserStatistics()
    {
        return null;
    }
}

