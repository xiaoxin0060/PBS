package com.xiaoxin.blog.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.admin.vo.AuthVo;
import com.xiaoxin.blog.web.admin.vo.CaptchaVo;


public interface AuthService extends IService<User> {


    CaptchaVo getCaptcha();

    String login(AuthVo authVo);
}
