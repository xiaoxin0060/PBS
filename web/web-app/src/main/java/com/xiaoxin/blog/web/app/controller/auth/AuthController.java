package com.xiaoxin.blog.web.app.controller.auth;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.LoginDto;
import com.xiaoxin.blog.web.app.dto.RegisterDto;
import com.xiaoxin.blog.web.app.service.AuthService;
import com.xiaoxin.blog.web.app.vo.CaptchaVo;
import com.xiaoxin.blog.web.app.vo.LoginVo;
import com.xiaoxin.blog.web.app.vo.TokenVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户认证")
@RequestMapping("/app/auth")
@RestController
@Slf4j
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<LoginVo> register(@RequestBody @Valid RegisterDto registerDto) {
        LoginVo loginVo = authService.register(registerDto);
        return Result.ok(loginVo);
    }
    
    @Operation(summary = "用户登录")
    @PostMapping("/login") 
    public Result<LoginVo> login(@RequestBody @Valid LoginDto loginDto) {
        LoginVo loginVo = authService.login(loginDto);
        return Result.ok(loginVo);
    }
    
    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result<CaptchaVo> getCaptcha(@RequestParam String type) {
        CaptchaVo captcha = authService.generateCaptcha(type);
        return Result.ok(captcha);
    }
    
    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public Result<Void> logout() {
        authService.logout();
        return Result.ok();
    }
    
    @Operation(summary = "刷新Token")
    @PostMapping("/refresh")
    public Result<TokenVo> refreshToken() {
        TokenVo tokenVo = authService.refreshToken();
        return Result.ok(tokenVo);
    }
}