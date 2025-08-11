package com.xiaoxin.blog.web.app.controller.content;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.enums.PopularType;
import com.xiaoxin.blog.web.app.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "文章内容")
@RequestMapping("/app/posts")
@RestController
public class PostController {
    
    @Autowired
    private ArticleService articleService;
    
    @Operation(summary = "获取文章列表")
    @GetMapping
    public Result<PageResult<ArticleListVo>> getArticles(ArticleQueryDto queryDto) {
        PageResult<ArticleListVo> articles = articleService.getArticleList(queryDto);
        return Result.ok(articles);
    }
    
    @Operation(summary = "获取文章详情")
    @GetMapping("/{id}")
    public Result<ArticleDetailVo> getArticleDetail(@PathVariable Long id) {
        ArticleDetailVo article = articleService.getArticleDetail(id);
        return Result.ok(article);
    }
    
    @Operation(summary = "获取热门文章")
    @GetMapping("/popular")
    public Result<List<PopularArticleVo>> getPopularArticles(
            @RequestParam PopularType type,
            @RequestParam Integer days,
            @RequestParam Integer limit) {
        List<PopularArticleVo> articles = articleService.getPopularArticles(type, days, limit);
        return Result.ok(articles);
    }
    
    @Operation(summary = "发布文章")
    @PostMapping
    public Result<Long> publishArticle(@RequestBody @Valid PublishArticleDto publishDto) {
        Long articleId = articleService.publishArticle(publishDto);
        return Result.ok(articleId);
    }
    
    @Operation(summary = "编辑文章") 
    @PutMapping("/{id}")
    public Result<Void> updateArticle(@PathVariable Long id, 
                                     @RequestBody @Valid UpdateArticleDto updateDto) {
        articleService.updateUserArticle(id, updateDto);
        return Result.ok();
    }
    
    @Operation(summary = "删除文章")
    @DeleteMapping("/{id}")
    public Result<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteUserArticle(id);
        return Result.ok();
    }
    
    @Operation(summary = "获取我的文章")
    @GetMapping("/my")
    public Result<PageResult<MyArticleVo>> getMyArticles(MyArticleQueryDto queryDto) {
        PageResult<MyArticleVo> articles = articleService.getMyArticles(queryDto);
        return Result.ok(articles);
    }
}