package com.xiaoxin.blog.web.admin.controller;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@Tag(name = "用户信息管理", description = "用户的增删改查、状态、角色、头像等管理接口")
public class UserController {

    @Operation(summary = "分页查询所有用户")
    @GetMapping("/selectAllUser")
    public Result selectAllUser() {
        return Result.ok();
    }

    @Operation(summary = "根据ID查询用户信息")
    @PostMapping("/selectUserById")
    public Result selectUserById(@Parameter(description = "用户ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "新增或修改用户")
    @PostMapping("/updateOrSaveUser")
    public Result updateOrSaveUser() {
        return Result.ok();
    }

    @Operation(summary = "删除用户(实际为禁用)")
    @GetMapping("/removeUser")
    public Result removeUser(@Parameter(description = "用户ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "更改用户状态（正常/禁用）")
    @PutMapping("/changeUserStatus")
    public Result changeUserStatus(
            @Parameter(description = "用户ID") long id,
            @Parameter(description = "状态，0-正常，1-禁用") int status) {
        return Result.ok();
    }

    @Operation(summary = "修改用户角色")
    @PutMapping("/changeUserRole")
    public Result changeUserRole(
            @Parameter(description = "用户ID") long id,
            @Parameter(description = "角色，0-普通用户，1-管理员") int role) {
        return Result.ok();
    }

    @Operation(summary = "修改用户头像")
    @PutMapping("/updateUserAvatar")
    public Result updateUserAvatar(
            @Parameter(description = "用户ID") long id,
            @Parameter(description = "头像URL") String avatar) {
        return Result.ok();
    }

    @Operation(summary = "重置用户密码")
    @PutMapping("/resetUserPassword")
    public Result resetUserPassword(
            @Parameter(description = "用户ID") long id,
            @Parameter(description = "新密码") String newPassword) {
        return Result.ok();
    }
}