package com.xiaoxin.blog.web.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.enums.PopularType;
import com.xiaoxin.blog.web.admin.service.ArticleService;
import com.xiaoxin.blog.web.admin.service.FileService;
import com.xiaoxin.blog.web.admin.vo.*;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/articles")  // 复数形式
@Tag(name = "文章信息管理", description = "文章的增删改查、发布、置顶、标签关联等操作")
public class ArticleController{
    @Autowired
    private ArticleService articleService;
    @Autowired
    private FileService fileService;

    @Operation(summary = "分页获取文章列表")
    @PostMapping("/page")
    public Result<IPage<ArticleVo>> getArticles(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(description = "文章查询条件") @RequestBody(required = false) ArticleQueryVo articleQueryVo)
    {
        // 分页获取文章列表
        IPage<ArticleVo> page = new Page<>(pageNum, pageSize);
        IPage<ArticleVo> result = articleService.getArticles(page, articleQueryVo);
        return Result.ok(result);
    }

    @Operation(summary = "获取文章详情")
    @GetMapping("/{id}")
    public Result<ArticleDetailVo> getArticleById(@Parameter(description = "文章ID") @PathVariable Long id)
    {
        // 获取文章详情，包括内容、标签等信息
        ArticleDetailVo result = articleService.getArticleById(id);
        return Result.ok(result);
    }

    @Operation(summary = "创建文章")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "multipart/form-data", schemaProperties = {@SchemaProperty(name = "title", schema = @Schema(type = "string", description = "标题")), @SchemaProperty(name = "content", schema = @Schema(type = "string", description = "内容")), @SchemaProperty(name = "categoryId", schema = @Schema(type = "integer", description = "分类ID")), @SchemaProperty(name = "userId", schema = @Schema(type = "integer", description = "用户ID")), @SchemaProperty(name = "coverImage", schema = @Schema(type = "string", format = "binary", description = "封面图片"))}))
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<Article> createArticle(@Parameter(description = "文章信息") @ModelAttribute @Valid
                                         CreatArticleVo creatArticleVo) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        // 创建文章
        Article article = new Article();
        String url = fileService.uploadFile(creatArticleVo.getCoverImage());
        BeanUtils.copyProperties(creatArticleVo, article);
        article.setCoverImage(url);
        articleService.save(article);
        return Result.ok(article);
    }

    @Operation(summary = "更新文章")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "multipart/form-data", schemaProperties = {@SchemaProperty(name = "title", schema = @Schema(type = "string", description = "标题")), @SchemaProperty(name = "content", schema = @Schema(type = "string", description = "内容")), @SchemaProperty(name = "categoryId", schema = @Schema(type = "integer", description = "分类ID")), @SchemaProperty(name = "userId", schema = @Schema(type = "integer", description = "用户ID")), @SchemaProperty(name = "coverImage", schema = @Schema(type = "string", format = "binary", description = "封面图片"))}))
    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result updateArticle(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "文章信息") @ModelAttribute @Valid
            UpdateArticleVo updateArticleVo) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        // 更新文章
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<Article>().eq(Article::getId, id);
        if(updateArticleVo.getCoverImage() != null){
            String url = fileService.uploadFile(updateArticleVo.getCoverImage());
            updateWrapper.set(Article::getCoverImage, url);
        }
        updateWrapper.set(updateArticleVo.getTitle() != null, Article::getTitle, updateArticleVo.getTitle())
                     .set(updateArticleVo.getContent() != null, Article::getContent, updateArticleVo.getContent())
                     .set(updateArticleVo.getCategoryId() != null,
                          Article::getCategoryId,
                          updateArticleVo.getCategoryId())
                     .set(updateArticleVo.getUserId() != null, Article::getUserId, updateArticleVo.getUserId());
        articleService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除文章")
    @DeleteMapping("/{id}")
    public Result deleteArticle(@Parameter(description = "文章ID") @PathVariable Long id)
    {
        // 逻辑删除文章
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<Article>().eq(Article::getId, id)
                                                                                       .set(Article::getDeleted, 1);
        articleService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "恢复已删除文章")
    @PutMapping("/{id}/restore")
    public Result restoreArticle(@Parameter(description = "文章ID") @PathVariable Long id)
    {
        // 恢复已删除文章
        articleService.restoreArticle(id);
        return Result.ok();
    }

    @Operation(summary = "更新文章发布状态")
    @PatchMapping("/{id}/status")
    public Result updateArticleStatus(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "状态(0-草稿,1-已发布)") @RequestParam Integer status)
    {
        // 更新文章状态（发布或撤回）
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<Article>().eq(Article::getId, id)
                                                                                       .set(Article::getStatus, status);
        articleService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "更新文章置顶状态")
    @PatchMapping("/{id}/top")
    public Result updateArticleTopStatus(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "是否置顶(0-否,1-是)") @RequestParam Integer isTop)
    {
        // 更新文章置顶状态
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<Article>().eq(Article::getId, id)
                                                                                       .set(Article::getIsTop, isTop);
        articleService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "为文章添加标签")
    @PostMapping("/{id}/tags")
    public Result addTagsToArticle(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "标签ID列表") @RequestBody List<Long> tagIds)
    {
        // 为文章添加标签
        articleService.addTagsToArticle(id, tagIds);
        return Result.ok();
    }

    @Operation(summary = "从文章移除标签")
    @DeleteMapping("/{id}/tags")
    public Result removeTagsFromArticle(
            @Parameter(description = "文章ID") @PathVariable Long id,
            @Parameter(description = "标签ID列表") @RequestBody List<Long> tagIds)
    {
        // 从文章移除标签
        articleService.removeTagsFromArticle(id, tagIds);
        return Result.ok();
    }

    @Operation(summary = "获取草稿箱文章")
    @GetMapping("/drafts")
    public Result<IPage<DraftArticleVo>> getDraftArticles(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize)
    {
        // 获取草稿状态的文章
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, 0)
               .select(Article::getId, Article::getTitle, Article::getUserId, Article::getCoverImage)
               .orderByDesc(Article::getId);
        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        IPage<Article> result = articleService.page(articlePage, wrapper);

        List<DraftArticleVo> articleVos = result.getRecords()
                                                .stream()
                                                .map(article -> new DraftArticleVo(article.getId(),
                                                                                   article.getTitle(),
                                                                                   article.getUserId(),
                                                                                   article.getCoverImage()))
                                                .collect(Collectors.toList());
        Page<DraftArticleVo> page = new Page<>(pageNum, pageSize, result.getTotal());
        page.setRecords(articleVos);
        return Result.ok(page);
    }
    @Operation(summary = "查询热门文章排行榜")
    @GetMapping("/popular")
    public Result<List<PopularArticleVo>> getPopularArticles(
            @Parameter(description = "排行榜类型")
            @RequestParam(defaultValue = "HOT") PopularType type,
            @Parameter(description = "时间范围(天)")
            @RequestParam(defaultValue = "30") @Min(1) @Max(365) Integer days,
            @Parameter(description = "查询数量")
            @RequestParam(defaultValue = "10") @Min(1) @Max(50) Integer limit) {

        List<PopularArticleVo> articles = articleService.getPopularArticles(type, days, limit);
        return Result.ok(articles);
    }
}