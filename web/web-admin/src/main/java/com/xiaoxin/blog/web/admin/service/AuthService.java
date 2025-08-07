package com.xiaoxin.blog.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.admin.vo.AuthVo;
import com.xiaoxin.blog.web.admin.vo.CaptchaVo;
import com.xiaoxin.blog.web.admin.vo.UserRegistVo;

import java.util.Map;


public interface AuthService extends IService<User> {


    CaptchaVo getCaptcha();

    Map<String, String> login(AuthVo authVo);

    void register(UserRegistVo userRegistVo);

    Map<String, String> refreshToken(String refreshToken);

    void resetPasswordByEmail(String email, String code, String newPassword);

    void getCode(String email);

    void changePassword(String oldPassword, String newPassword);
}
