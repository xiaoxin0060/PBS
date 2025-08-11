package com.xiaoxin.blog.web.app.service;

import com.xiaoxin.blog.web.app.dto.LoginDto;
import com.xiaoxin.blog.web.app.dto.RegisterDto;
import com.xiaoxin.blog.web.app.vo.CaptchaVo;
import com.xiaoxin.blog.web.app.vo.LoginVo;
import com.xiaoxin.blog.web.app.vo.TokenVo;

public interface AuthService{
    LoginVo register(RegisterDto registerDto);

    LoginVo login(LoginDto loginDto);

    CaptchaVo generateCaptcha(String type);

    void logout();

    TokenVo refreshToken();
}
