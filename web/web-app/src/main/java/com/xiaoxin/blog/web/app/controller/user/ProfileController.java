package com.xiaoxin.blog.web.app.controller.user;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.ChangePasswordDto;
import com.xiaoxin.blog.web.app.dto.UpdateProfileDto;
import com.xiaoxin.blog.web.app.service.UserService;
import com.xiaoxin.blog.web.app.vo.UserProfileVo;
import com.xiaoxin.blog.web.app.vo.UserStatisticsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "个人中心")
@RequestMapping("/app/profile")
@RestController
public class ProfileController {
    
    @Autowired
    private UserService userService;
    
    @Operation(summary = "获取个人信息")
    @GetMapping
    public Result<UserProfileVo> getProfile() {
        UserProfileVo profile = userService.getCurrentUserProfile();
        return Result.ok(profile);
    }
    
    @Operation(summary = "更新个人信息")
    @PutMapping
    public Result<Void> updateProfile(@RequestBody @Valid UpdateProfileDto updateDto) {
        userService.updateProfile(updateDto);
        return Result.ok();
    }
    
    @Operation(summary = "上传头像")
    @PostMapping("/avatar")
    public Result<String> uploadAvatar(@RequestParam MultipartFile file) {
        String avatarUrl = userService.uploadAvatar(file);
        return Result.ok(avatarUrl);
    }
    
    @Operation(summary = "修改密码")
    @PostMapping("/change-password")
    public Result<Void> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
        return Result.ok();
    }
    
    @Operation(summary = "获取我的统计")
    @GetMapping("/statistics")
    public Result<UserStatisticsVo> getStatistics() {
        UserStatisticsVo statistics = userService.getUserStatistics();
        return Result.ok(statistics);
    }
}