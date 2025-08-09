package com.xiaoxin.blog.web.admin.controller;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.entity.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")  // 使用复数名词
@Tag(name = "分类信息管理", description = "分类的增删改查、排序等接口")
public class CategoryController {

    @Operation(summary = "获取所有分类")
    @GetMapping
    public Result<List<Category>> getAllCategories() {
        // 获取所有分类列表（自动过滤deleted=0）
        return Result.ok();
    }

    @Operation(summary = "根据ID获取分类")
    @GetMapping("/{id}")  // 使用路径参数
    public Result<Category> getCategoryById(
            @Parameter(description = "分类ID") @PathVariable Long id) {
        // 根据ID获取分类
        return Result.ok();
    }

    @Operation(summary = "创建分类")
    @PostMapping
    public Result<Category> createCategory(
            @Parameter(description = "分类信息") @RequestBody @Valid Category category) {
        // 创建新分类
        return Result.ok();
    }

    @Operation(summary = "更新分类")
    @PutMapping("/{id}")
    public Result<Category> updateCategory(
            @Parameter(description = "分类ID") @PathVariable Long id,
            @Parameter(description = "分类信息") @RequestBody @Valid Category category) {
        // 更新分类
        return Result.ok();
    }

    @Operation(summary = "更新分类排序")
    @PatchMapping("/{id}/sort")  // 使用PATCH更新部分资源
    public Result updateCategorySort(
            @Parameter(description = "分类ID") @PathVariable Long id,
            @Parameter(description = "排序值") @RequestParam Integer sort) {
        // 更新分类排序值
        return Result.ok();
    }

    @Operation(summary = "逻辑删除分类")
    @DeleteMapping("/{id}")
    public Result deleteCategory(
            @Parameter(description = "分类ID") @PathVariable Long id) {
        // 逻辑删除分类（设置deleted=1）
        return Result.ok();
    }

    @Operation(summary = "恢复已删除的分类")
    @PutMapping("/{id}/restore")
    public Result restoreCategory(
            @Parameter(description = "分类ID") @PathVariable Long id) {
        // 恢复已删除的分类（设置deleted=0）
        return Result.ok();
    }
}