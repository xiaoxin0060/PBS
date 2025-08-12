package com.xiaoxin.blog.web.app.controller.auth;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.ForgotPasswordSendCodeDto;
import com.xiaoxin.blog.web.app.dto.LoginDto;
import com.xiaoxin.blog.web.app.dto.RegisterDto;
import com.xiaoxin.blog.web.app.dto.ResetPasswordDto;
import com.xiaoxin.blog.web.app.service.AuthService;
import com.xiaoxin.blog.web.app.vo.CaptchaVo;
import com.xiaoxin.blog.web.app.vo.EmailCodeVo;
import com.xiaoxin.blog.web.app.vo.LoginVo;
import com.xiaoxin.blog.web.app.vo.TokenVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户认证", description = "用户注册、登录、验证码等认证相关接口")
@RequestMapping("/app/auth")
@RestController
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "用户注册",
            description = "新用户注册，需要邮箱验证和图形验证码验证")
    @PostMapping("/register")
    public Result<LoginVo> register(@RequestBody @Valid RegisterDto registerDto) {
        log.info("用户注册请求: {}", registerDto.getUsername());
        LoginVo loginVo = authService.register(registerDto);
        return Result.ok(loginVo);
    }

    @Operation(summary = "用户登录",
            description = "用户登录认证，支持用户名或邮箱登录，需要图形验证码")
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody @Valid LoginDto loginDto) {
        log.info("用户登录请求: {}", loginDto.getUsername());
        LoginVo loginVo = authService.login(loginDto);
        return Result.ok(loginVo);
    }

    @Operation(summary = "获取图形验证码",
            description = "获取图形验证码，用于登录、注册等场景验证")
    @GetMapping("/captcha")
    public Result<CaptchaVo> getCaptcha() {
        log.info("获取验证码请求");
        CaptchaVo captcha = authService.getCaptcha();
        return Result.ok(captcha);
    }

    @Operation(summary = "退出登录",
            description = "用户退出登录，清除Token和Redis会话")
    @PostMapping("/logout")
    public Result<Void> logout() {
        log.info("用户退出登录");
        authService.logout();
        return Result.ok();
    }

    @Operation(summary = "刷新访问令牌",
            description = "使用RefreshToken获取新的AccessToken")
    @PostMapping("/refresh")
    public Result<TokenVo> refreshToken(@RequestParam @NotBlank String refreshToken) {
        log.info("刷新Token请求");
        TokenVo tokenVo = authService.refreshToken(refreshToken);
        return Result.ok(tokenVo);
    }

    @Operation(summary = "发送重置密码验证码",
            description = "忘记密码时，向邮箱发送验证码")
    @PostMapping("/forgot-password/send-code")
    public Result<EmailCodeVo> sendResetPasswordCode(@RequestBody @Valid ForgotPasswordSendCodeDto sendCodeDto) {
        log.info("发送重置密码验证码: {}", sendCodeDto.getEmail());
        EmailCodeVo emailCodeVo = authService.sendResetPasswordCode(sendCodeDto.getEmail());
        return Result.ok(emailCodeVo);
    }

    @Operation(summary = "重置密码",
            description = "通过邮箱验证码重置密码")
    @PostMapping("/forgot-password/reset")
    public Result<Void> resetPassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        log.info("重置密码请求: {}", resetPasswordDto.getEmail());
        authService.resetPassword(resetPasswordDto);
        return Result.ok();
    }
}