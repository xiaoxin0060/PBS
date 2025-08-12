package com.xiaoxin.blog.web.app.service;

import com.xiaoxin.blog.web.app.dto.LoginDto;
import com.xiaoxin.blog.web.app.dto.RegisterDto;
import com.xiaoxin.blog.web.app.dto.ResetPasswordDto;
import com.xiaoxin.blog.web.app.vo.CaptchaVo;
import com.xiaoxin.blog.web.app.vo.EmailCodeVo;
import com.xiaoxin.blog.web.app.vo.LoginVo;
import com.xiaoxin.blog.web.app.vo.TokenVo;

public interface AuthService{
    LoginVo register(RegisterDto registerDto);

    LoginVo login(LoginDto loginDto);

    void logout();

    TokenVo refreshToken(String refreshToken);

    CaptchaVo getCaptcha();

    EmailCodeVo sendResetPasswordCode(String email);

    void resetPassword(ResetPasswordDto resetPasswordDto);
}
