package com.xiaoxin.blog.web.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.entity.Tag;
import com.xiaoxin.blog.web.admin.service.ArticleService;
import com.xiaoxin.blog.web.admin.service.ArticleTagService;
import com.xiaoxin.blog.web.admin.service.TagService;
import com.xiaoxin.blog.web.admin.vo.ArticleTagVo;
import com.xiaoxin.blog.web.admin.vo.TagVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/articleTag")
@io.swagger.v3.oas.annotations.tags.Tag(name = "文章标签关联管理", description = "文章与标签的关联管理接口")
@Validated
public class ArticleTagController {

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Operation(summary = "查询文章关联的所有标签")
    @GetMapping("/article/{articleId}")
    public Result<List<TagVo>> getTagsByArticleId(
            @Parameter(description = "文章ID") @PathVariable @Min(1) Long articleId) {
        // 实现查询文章关联的所有标签
        List<TagVo> result=articleTagService.getTagsByArticleId(articleId);
        return Result.ok(result);
    }

    @Operation(summary = "查询标签关联的所有文章")
    @GetMapping("/tag/{tagId}")
    public Result<IPage<Article>> getArticlesByTagId(
            @Parameter(description = "标签ID") @PathVariable @Min(1) Long tagId,
            @Parameter(description = "当前页码") @RequestParam(defaultValue = "1") @Min(1) Long current,
            @Parameter(description = "每页记录数") @RequestParam(defaultValue = "10") @Min(1) Long size) {
        // 实现分页查询标签关联的所有文章
        return Result.ok();
    }

    @Operation(summary = "为文章批量添加标签")
    @PostMapping("/batch")
    public Result batchAddTags(@RequestBody @Validated ArticleTagVo articleTagVO) {
        // 实现为文章批量添加标签
        return Result.ok();
    }

    @Operation(summary = "移除文章的标签")
    @DeleteMapping("/{articleId}/{tagId}")
    public Result removeTag(
            @Parameter(description = "文章ID") @PathVariable @Min(1) Long articleId,
            @Parameter(description = "标签ID") @PathVariable @Min(1) Long tagId) {
        // 实现移除文章的标签
        return Result.ok();
    }

    @Operation(summary = "查询被特定标签标记最多的文章")
    @GetMapping("/popular/articles")
    public Result<List<Article>> getPopularArticlesByTags(
            @Parameter(description = "查询数量") @RequestParam(defaultValue = "5") @Min(1) Integer limit) {
        // 实现查询被标记最多的热门文章
        return Result.ok();
    }

    @Operation(summary = "查询被使用最多的标签")
    @GetMapping("/popular/tags")
    public Result<List<Tag>> getPopularTags(
            @Parameter(description = "查询数量") @RequestParam(defaultValue = "10") @Min(1) Integer limit) {
        // 实现查询使用最多的热门标签
        return Result.ok();
    }

    @Operation(summary = "恢复已删除的文章标签关联")
    @PutMapping("/restore/{articleId}/{tagId}")
    public Result restoreArticleTag(
            @Parameter(description = "文章ID") @PathVariable @Min(1) Long articleId,
            @Parameter(description = "标签ID") @PathVariable @Min(1) Long tagId) {
        // 实现恢复已删除的文章标签关联
        return Result.ok();
    }
}