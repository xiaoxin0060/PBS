package com.xiaoxin.blog.web.admin.controller;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tag")
@Tag(name = "标签信息管理", description = "标签的增删改查接口")
public class TagController {

    @Operation(summary = "查询所有标签")
    @GetMapping("/selectAllTag")
    public Result selectAllTag() {
        return Result.ok();
    }

    @Operation(summary = "根据ID查询标签")
    @PostMapping("/selectTagById")
    public Result selectTagById(@Parameter(description = "标签ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "新增或修改标签")
    @PostMapping("/updateOrSaveTag")
    public Result updateOrSaveTag() {
        return Result.ok();
    }

    @Operation(summary = "逻辑删除标签")
    @GetMapping("/removeTag")
    public Result removeTag(@Parameter(description = "标签ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "恢复逻辑删除的标签")
    @PutMapping("/recoverTag")
    public Result recoverTag(@Parameter(description = "标签ID") long id) {
        return Result.ok();
    }
}