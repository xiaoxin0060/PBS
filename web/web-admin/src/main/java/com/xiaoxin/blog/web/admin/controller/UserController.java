package com.xiaoxin.blog.web.admin.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.admin.service.FileService;
import com.xiaoxin.blog.web.admin.service.UserService;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/admin/user")
@Tag(name = "用户信息管理", description = "用户的增删改查、状态、角色、头像等管理接口")
@Validated
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Operation(summary = "分页查询所有用户")
    @GetMapping("/list")
    public Result<IPage<User>> listUsers(
            @Parameter(description = "当前页码") @RequestParam(defaultValue = "1") @Min(1) long current,
            @Parameter(description = "每页记录数") @RequestParam(defaultValue = "10") @Min(1) long size)
    {

        IPage<User> page = new Page<>(current, size);
        IPage<User> pageResult = userService.page(page);
        return Result.ok(pageResult);
    }

    @Operation(summary = "根据ID查询用户信息")
    @GetMapping("/{id}")
    public Result<User> getUserById(@Parameter(description = "用户ID") @PathVariable @Min(1) long id)
    {

        User user = userService.getById(id);
        if(user == null){
            throw new BlogException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        return Result.ok(user);
    }

    @Operation(summary = "新增或修改用户")
    @PostMapping("/updateOrSaveUser")
    public Result updateOrSaveUser(@Parameter(description = "用户信息") @RequestBody User user)
    {
        userService.saveOrUpdate(user);
        return Result.ok();
    }

    @Operation(summary = "禁用用户")
    @PutMapping("/{id}/disable")
    public Result disableUser(@Parameter(description = "用户ID") @PathVariable @Min(1) long id)
    {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getStatus, 1);
        userService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "更改用户状态（正常/禁用）")
    @PutMapping("/{id}/status")
    public Result changeUserStatus(
            @Parameter(description = "用户ID") @PathVariable @Min(1) long id,
            @Parameter(description = "状态，0-正常，1-禁用") @RequestParam @NotNull Integer status)
    {

        if(status != 0 && status != 1){
            throw new BlogException(ResultCodeEnum.PARAM_ERROR);
        }
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getStatus, status);
        userService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "修改用户角色")
    @PutMapping("/{id}/role")
    public Result changeUserRole(
            @Parameter(description = "用户ID") @PathVariable @Min(1) long id,
            @Parameter(description = "角色，0-普通用户，1-管理员") @RequestParam @NotNull Integer role)
    {

        // 参数校验
        if(role != 0 && role != 1){
            throw new BlogException(ResultCodeEnum.PARAM_ERROR);
        }

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getRole, role);

        userService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "修改用户头像")
    @PostMapping("/{id}/avatar")
    public Result updateUserAvatar(
            @Parameter(description = "用户ID") @PathVariable @Min(1) long id,
            @Parameter(description = "头像图片") @RequestParam
            MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        String url = fileService.uploadFile(file);
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getAvatar, url);

        userService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "重置用户密码")
    @PutMapping("/{id}/password")
    public Result resetUserPassword(
            @Parameter(description = "用户ID") @PathVariable @Min(1) long id,
            @Parameter(description = "新密码") @RequestParam @NotBlank String newPassword)
    {

        // 密码应该进行加密处理，这里省略了加密逻辑
        // String encodedPassword = passwordEncoder.encode(newPassword);

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getPassword, newPassword); // 应改为加密后的密码

        userService.update(updateWrapper);
        return Result.ok();
    }
}
