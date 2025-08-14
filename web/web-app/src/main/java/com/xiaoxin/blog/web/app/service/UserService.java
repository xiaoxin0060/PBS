package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.app.dto.ChangePasswordDto;
import com.xiaoxin.blog.web.app.dto.UpdateProfileDto;
import com.xiaoxin.blog.web.app.vo.*;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 小新
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface UserService extends IService<User> {

    UserPageVo getUserPage(Long userId);

    IPage<UserArticleVo> getUserArticles(Long userId, Integer cur, Integer size);



    IPage<UserSearchVo> searchUsers(String keyword, Integer cur, Integer size);

    UserProfileVo getCurrentUserProfile();

    void updateProfile(UpdateProfileDto updateDto);

    String uploadAvatar(MultipartFile file);

    void changePassword(ChangePasswordDto changePasswordDto);

    UserStatisticsVo getUserStatistics();
}
