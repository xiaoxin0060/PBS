package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.app.dto.ChangePasswordDto;
import com.xiaoxin.blog.web.app.dto.UpdateProfileDto;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.mapper.UserMapper;
import com.xiaoxin.blog.web.app.service.AuthService;
import com.xiaoxin.blog.web.app.service.FileService;
import com.xiaoxin.blog.web.app.service.UserService;
import com.xiaoxin.blog.web.app.vo.*;
import io.minio.errors.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private FileService fileService;
    @Autowired
    private AuthService authService;

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
        User user = userMapper.selectById(LoginUserHolder.get().getUserId());
        UserProfileVo vo = new UserProfileVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    @Override
    public void updateProfile(UpdateProfileDto updateDto)
    {
        User user = userMapper.selectById(LoginUserHolder.get().getUserId());
        BeanUtils.copyProperties(updateDto, user);
        userMapper.updateById(user);
    }

    @Override
    public String uploadAvatar(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        String url = fileService.uploadImage(file);
        User user = userMapper.selectById(LoginUserHolder.get().getUserId());
        user.setAvatar(url);
        userMapper.updateById(user);
        return url;
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto)
    {
        Long userId = LoginUserHolder.get().getUserId();
        User user = userMapper.selectById(userId);
        if(!Objects.equals(user.getPassword(), changePasswordDto.getOriginalPassword())) throw new BlogException(ResultCodeEnum.ADMIN_PASSWORD_ERROR);
        if(Objects.equals(user.getPassword(), changePasswordDto.getNewPassword())){
            throw new BlogException(ResultCodeEnum.APP_PASSWORD_SAME_AS_OLD);
        }
        user.setPassword(changePasswordDto.getNewPassword());
        userMapper.updateById(user);
    }

    @Override
    public UserStatisticsVo getUserStatistics()
    {
        Long userId = LoginUserHolder.get().getUserId();
        return userMapper.getUserStatistics(userId);
    }
}

