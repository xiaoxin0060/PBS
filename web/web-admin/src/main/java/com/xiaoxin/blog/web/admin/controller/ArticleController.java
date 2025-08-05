package com.xiaoxin.blog.web.admin.controller;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/article")
@Tag(name = "文章信息管理", description = "文章的增删改查、发布、置顶、标签关联等操作")
public class ArticleController {

    @Operation(summary = "分页查询所有文章")
    @GetMapping("/selectAllArticle")
    public Result selectAllArticle() {
        return Result.ok();
    }

    @Operation(summary = "根据ID查询文章详情")
    @PostMapping("/selectArticleById")
    public Result selectArticleById(@Parameter(description = "文章ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "新增或修改文章")
    @PostMapping("/updateOrSaveArticle")
    public Result updateOrSaveArticle() {
        return Result.ok();
    }

    @Operation(summary = "逻辑删除文章")
    @GetMapping("/removeArticle")
    public Result removeArticle(@Parameter(description = "文章ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "恢复逻辑删除的文章")
    @PutMapping("/recoverArticle")
    public Result recoverArticle(@Parameter(description = "文章ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "发布或撤回文章")
    @PutMapping("/publishArticle")
    public Result publishArticle(
            @Parameter(description = "文章ID") long id,
            @Parameter(description = "状态，0-草稿，1-已发布") int status) {
        return Result.ok();
    }

    @Operation(summary = "置顶或取消置顶文章")
    @PutMapping("/topArticle")
    public Result topArticle(
            @Parameter(description = "文章ID") long id,
            @Parameter(description = "是否置顶，0-否，1-是") int isTop) {
        return Result.ok();
    }

    @Operation(summary = "批量给文章添加标签")
    @PutMapping("/addTagsToArticle")
    public Result addTagsToArticle(
            @Parameter(description = "文章ID") long articleId,
            @Parameter(description = "标签ID，多个用逗号分隔") String tagIds) {
        return Result.ok();
    }

    @Operation(summary = "批量移除文章标签")
    @PutMapping("/removeTagsFromArticle")
    public Result removeTagsFromArticle(
            @Parameter(description = "文章ID") long articleId,
            @Parameter(description = "标签ID，多个用逗号分隔") String tagIds) {
        return Result.ok();
    }
}