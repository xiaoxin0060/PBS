package com.xiaoxin.blog.web.admin.controller;

import com.xiaoxin.blog.common.login.LoginUser;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.admin.service.AuthService;
import com.xiaoxin.blog.web.admin.service.UserService;
import com.xiaoxin.blog.web.admin.vo.CaptchaVo;
import com.xiaoxin.blog.web.admin.vo.AuthVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
@Tag(name = "登录认证管理", description = "登录认证、注册、修改密码等接口")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;


    @Operation(summary = "获取验证码")
    @PostMapping("/getCaptcha")
    public Result<CaptchaVo> getCaptcha() {
        return Result.ok(authService.getCaptcha());
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<String> login(
            @Parameter(description = "登录信息") @RequestBody AuthVo authVo) {
        String jwt =authService.login(authVo);
        return Result.ok(jwt);
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(
            @Parameter(description = "用户名") String username,
            @Parameter(description = "密码") String password,
            @Parameter(description = "邮箱") String email) {
        return Result.ok();
    }

    @Operation(summary = "刷新Token")
    @PostMapping("/refreshToken")
    public Result refreshToken(@Parameter(description = "刷新Token") String refreshToken) {
        return Result.ok();
    }

    @Operation(summary = "通过邮箱重置密码")
    @PostMapping("/resetPasswordByEmail")
    public Result resetPasswordByEmail(
            @Parameter(description = "邮箱") String email,
            @Parameter(description = "验证码") String code,
            @Parameter(description = "新密码") String newPassword) {
        return Result.ok();
    }

    @Operation(summary = "修改密码")
    @PutMapping("/changePassword")
    public Result changePassword(
            @Parameter(description = "旧密码") String oldPassword,
            @Parameter(description = "新密码") String newPassword) {
        return Result.ok();
    }

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/profile")
    public Result<User> getUserProfile() {
        LoginUser loginUser = LoginUserHolder.get();
        User user = userService.getById(loginUser.getUserId());
        return Result.ok(user);
    }
}