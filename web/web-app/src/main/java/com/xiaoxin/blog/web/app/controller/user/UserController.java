package com.xiaoxin.blog.web.app.controller.user;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户信息")
@RequestMapping("/app/users")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Operation(summary = "获取用户主页")
    @GetMapping("/{userId}")
    public Result<UserPageVo> getUserPage(@PathVariable Long userId) {
        UserPageVo userPage = userService.getUserPage(userId);
        return Result.ok(userPage);
    }
    
    @Operation(summary = "获取用户文章")
    @GetMapping("/{userId}/articles")
    public Result<PageResult<UserArticleVo>> getUserArticles(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        
        PageResult<UserArticleVo> articles = userService.getUserArticles(userId, page, size);
        return Result.ok(articles);
    }
    
    @Operation(summary = "获取用户动态")
    @GetMapping("/{userId}/activities")
    public Result<PageResult<UserActivityVo>> getUserActivities(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {
        
        PageResult<UserActivityVo> activities = userService.getUserActivities(userId, page, size);
        return Result.ok(activities);
    }
    
    @Operation(summary = "搜索用户")
    @GetMapping("/search")
    public Result<PageResult<UserSearchVo>> searchUsers(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        
        PageResult<UserSearchVo> users = userService.searchUsers(keyword, page, size);
        return Result.ok(users);
    }
}