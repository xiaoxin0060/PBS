package com.xiaoxin.blog.web.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.entity.ArticleTag;
import com.xiaoxin.blog.web.admin.service.ArticleService;
import com.xiaoxin.blog.web.admin.service.ArticleTagService;
import com.xiaoxin.blog.web.admin.vo.SimpleArticleVo;
import com.xiaoxin.blog.web.admin.vo.TagVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/articleTag")
@io.swagger.v3.oas.annotations.tags.Tag(name = "文章标签关联管理", description = "文章与标签的关联管理接口")
@Validated
public class ArticleTagController{

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleService articleService;


    @Operation(summary = "查询文章关联的所有标签")
    @GetMapping("/article/{articleId}")
    public Result<List<TagVo>> getTagsByArticleId(
            @Parameter(description = "文章ID") @PathVariable @Min(1) Long articleId)
    {
        // 实现查询文章关联的所有标签
        List<TagVo> result = articleTagService.getTagsByArticleId(articleId);
        return Result.ok(result);
    }

    @Operation(summary = "查询标签关联的所有文章")
    @GetMapping("/tag/{tagId}")
    public Result<IPage<SimpleArticleVo>> getArticlesByTagId(
            @Parameter(description = "标签ID") @PathVariable @Min(1) Long tagId,
            @Parameter(description = "当前页码") @RequestParam(defaultValue = "1") @Min(1) Long current,
            @Parameter(description = "每页记录数") @RequestParam(defaultValue = "10") @Min(1) Long size)
    {
        // 实现分页查询标签关联的所有文章
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getTagId,
                tagId);
        Page<ArticleTag> page = new Page<>(current, size);
        Page<ArticleTag> result = articleTagService.page(page, queryWrapper);
        List<Long> ArticleIds = result.getRecords().stream().map(ArticleTag::getArticleId).collect(Collectors.toList());
        List<Article> articles = articleService.listByIds(ArticleIds);

        List<SimpleArticleVo> voList = articles.stream().map(article -> new SimpleArticleVo(article.getId(),
                article.getTitle(),
                article.getUserId(),
                article.getCoverImage())).collect(Collectors.toList());
        Page<SimpleArticleVo> voPage = new Page<>(current, size, result.getTotal());
        voPage.setRecords(voList);
        return Result.ok(voPage);
    }

    @Operation(summary = "为文章批量添加标签")
    @PostMapping("/batch")
    public Result batchAddTags(
            @Parameter(description = "文章ID") @RequestParam @Validated Long articleId,
            @Parameter(description = "标签ID列表") @RequestParam @Validated List<Long> tagIds)
    {
        // 实现为文章批量添加标签
        Article article = articleService.getById(articleId);
        if(article == null) return Result.fail("文章不存在");
        articleTagService.batchAddTags(articleId, tagIds);
        return Result.ok();
    }

    @Operation(summary = "移除文章的标签")
    @DeleteMapping("/{articleId}/{tagId}")
    public Result removeTag(
            @Parameter(description = "文章ID") @PathVariable @Min(1) Long articleId,
            @Parameter(description = "标签ID") @PathVariable @Min(1) Long tagId)
    {
        // 实现移除文章的标签
        articleTagService.remove(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleId)
                                                                     .eq(ArticleTag::getTagId, tagId));
        return Result.ok();
    }

    @Operation(summary = "恢复已删除的文章标签关联")
    @PutMapping("/restore/{articleId}/{tagId}")
    public Result restoreArticleTag(
            @Parameter(description = "文章ID") @PathVariable @Min(1) Long articleId,
            @Parameter(description = "标签ID") @PathVariable @Min(1) Long tagId)
    {
        // 实现恢复已删除的文章标签关联
        articleTagService.restoreArticleTag(articleId, tagId);
        return Result.ok();
    }
}