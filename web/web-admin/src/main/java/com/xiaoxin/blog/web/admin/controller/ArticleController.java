package com.xiaoxin.blog.web.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.web.admin.service.ArticleService;
import com.xiaoxin.blog.web.admin.vo.ArticleDetailVo;
import com.xiaoxin.blog.web.admin.vo.ArticleQueryVo;
import com.xiaoxin.blog.web.admin.vo.ArticleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/articles")  // 复数形式
@Tag(name = "文章信息管理", description = "文章的增删改查、发布、置顶、标签关联等操作")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Operation(summary = "分页获取文章列表")
    @PostMapping("/page")
    public Result<IPage<ArticleVo>> getArticles(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(description = "文章查询条件") @RequestBody(required = false) ArticleQueryVo articleQueryVo) {
        // 分页获取文章列表
        IPage<ArticleVo> page = new Page<>(pageNum,pageSize);
        IPage<ArticleVo> result= articleService.getArticles(page,articleQueryVo);
        return Result.ok(result);
    }

    @Operation(summary = "获取文章详情")
    @GetMapping("/{id}")
    public Result<ArticleDetailVo> getArticleById(
            @Parameter(description = "文章ID") @PathVariable Long id) {
        // 获取文章详情，包括内容、标签等信息
        return Result.ok();
    }

    @Operation(summary = "创建文章")
    @PostMapping
    public Result<Article> createArticle(
            @Parameter(description = "文章信息") @RequestBody @Valid Article article) {
        // 创建文章
        return Result.ok();
    }

    @Operation(summary = "更新文章")
    @PutMapping("/{id}")
    public Result<Article> updateArticle(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "文章信息") @RequestBody @Valid Article article) {
        // 更新文章
        return Result.ok();
    }

    @Operation(summary = "逻辑删除文章")
    @DeleteMapping("/{id}")
    public Result deleteArticle(
            @Parameter(description = "文章ID") @PathVariable Long id) {
        // 逻辑删除文章
        return Result.ok();
    }

    @Operation(summary = "恢复已删除文章")
    @PutMapping("/{id}/restore")
    public Result restoreArticle(
            @Parameter(description = "文章ID") @PathVariable Long id) {
        // 恢复已删除文章
        return Result.ok();
    }

    @Operation(summary = "更新文章发布状态")
    @PatchMapping("/{id}/status")
    public Result updateArticleStatus(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "状态(0-草稿,1-已发布)") @RequestParam Integer status) {
        // 更新文章状态（发布或撤回）
        return Result.ok();
    }

    @Operation(summary = "更新文章置顶状态")
    @PatchMapping("/{id}/top")
    public Result updateArticleTopStatus(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "是否置顶(0-否,1-是)") @RequestParam Integer isTop) {
        // 更新文章置顶状态
        return Result.ok();
    }

    @Operation(summary = "为文章添加标签")
    @PostMapping("/{id}/tags")
    public Result addTagsToArticle(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "标签ID列表") @RequestBody List<Long> tagIds) {
        // 为文章添加标签
        return Result.ok();
    }

    @Operation(summary = "从文章移除标签")
    @DeleteMapping("/{id}/tags")
    public Result removeTagsFromArticle(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "标签ID列表") @RequestBody List<Long> tagIds) {
        // 从文章移除标签
        return Result.ok();
    }

    @Operation(summary = "获取草稿箱文章")
    @GetMapping("/drafts")
    public Result<IPage<Article>> getDraftArticles(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize) {
        // 获取草稿状态的文章
        return Result.ok();
    }
}