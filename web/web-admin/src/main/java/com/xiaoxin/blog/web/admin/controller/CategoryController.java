package com.xiaoxin.blog.web.admin.controller;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@Tag(name = "分类信息管理", description = "分类的增删改查、排序等接口")
public class CategoryController {

    @Operation(summary = "查询所有分类")
    @GetMapping("/selectAllCategory")
    public Result selectAllCategory() {
        return Result.ok();
    }

    @Operation(summary = "根据ID查询分类")
    @PostMapping("/selectCategoryById")
    public Result selectCategoryById(@Parameter(description = "分类ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "新增或修改分类")
    @PostMapping("/updateOrSaveCategory")
    public Result updateOrSaveCategory() {
        return Result.ok();
    }

    @Operation(summary = "逻辑删除分类")
    @GetMapping("/removeCategory")
    public Result removeCategory(@Parameter(description = "分类ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "恢复逻辑删除的分类")
    @PutMapping("/recoverCategory")
    public Result recoverCategory(@Parameter(description = "分类ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "调整分类排序值")
    @PutMapping("/sortCategory")
    public Result sortCategory(
            @Parameter(description = "分类ID") long id,
            @Parameter(description = "排序值") int sort) {
        return Result.ok();
    }
}