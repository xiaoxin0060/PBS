package com.xiaoxin.blog.web.app.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.service.UserService;
import com.xiaoxin.blog.web.app.vo.UserArticleVo;
import com.xiaoxin.blog.web.app.vo.UserPageVo;
import com.xiaoxin.blog.web.app.vo.UserSearchVo;
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
    public Result<IPage<UserArticleVo>> getUserArticles(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") Integer cur,
            @RequestParam(defaultValue = "10") Integer size) {

        IPage<UserArticleVo> articles = userService.getUserArticles(userId, cur, size);
        return Result.ok(articles);
    }

    
    @Operation(summary = "搜索用户")
    @GetMapping("/search")
    public Result<IPage<UserSearchVo>> searchUsers(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") Integer cur,
            @RequestParam(defaultValue = "10") Integer size) {

        IPage<UserSearchVo> users = userService.searchUsers(keyword, cur, size);
        return Result.ok(users);
    }
}