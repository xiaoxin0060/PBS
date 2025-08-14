package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.app.dto.ChangePasswordDto;
import com.xiaoxin.blog.web.app.dto.UpdateProfileDto;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.mapper.UserMapper;
import com.xiaoxin.blog.web.app.service.UserService;
import com.xiaoxin.blog.web.app.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小新
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2025-08-04 00:14:52
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public UserPageVo getUserPage(Long userId)
    {
        User user = userMapper.selectById(userId);
        UserPageVo userPageVo = new UserPageVo();
        BeanUtils.copyProperties(user, userPageVo);
        return userPageVo;
    }

    @Override
    public IPage<UserArticleVo> getUserArticles(Long userId, Integer cur, Integer size)
    {
        IPage<Article> page = new Page<>(cur, size);

        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<Article>().eq(Article::getUserId, userId)
                                                                               .eq(Article::getStatus, 1);
        IPage<Article> articleIPage = articleMapper.selectPage(page, wrapper);
        List<UserArticleVo> result = articleIPage.getRecords().stream().map(article -> {
            UserArticleVo vo = new UserArticleVo();
            BeanUtils.copyProperties(article, vo);
            return vo;
        }).collect(Collectors.toList());
        IPage<UserArticleVo> voPage = new Page<>(cur, size);
        voPage.setRecords(result);
        voPage.setTotal(articleIPage.getTotal());
        return voPage;
    }


    @Override
    public IPage<UserSearchVo> searchUsers(String keyword, Integer cur, Integer size)
    {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().like(User::getUsername, keyword)
                                                                              .eq(User::getStatus, 0);
        IPage<User> page = new Page<>(cur, size);
        IPage<User> userIPage = userMapper.selectPage(page, wrapper);
        List<UserSearchVo> result = userIPage.getRecords().stream().map(user -> {
            UserSearchVo vo = new UserSearchVo();
//            System.out.println(user.getUsername());
            BeanUtils.copyProperties(user, vo);
            return vo;
        }).collect(Collectors.toList());
        IPage<UserSearchVo> voPage = new Page<>(cur, size);
        voPage.setRecords(result);
        voPage.setTotal(userIPage.getTotal());
        return voPage;
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

