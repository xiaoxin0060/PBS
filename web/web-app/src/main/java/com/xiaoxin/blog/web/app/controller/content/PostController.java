package com.xiaoxin.blog.web.app.controller.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.dto.UpdateArticleDto;
import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.vo.ArticleDetailVo;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.MyArticleVo;
import com.xiaoxin.blog.web.app.vo.PopularArticleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "文章内容")
@RequestMapping("/app/posts")
@RestController
public class PostController {
    
    @Autowired
    private ArticleService articleService;
    
    @Operation(summary = "获取文章列表")
    @GetMapping
    public Result<IPage<ArticleListVo>> getArticles(@Parameter(description = "页码")@RequestParam Integer pageNum,
                                                     @Parameter(description = "每页大小")@RequestParam Integer pageSize) {
        ArticleQueryDto queryDto = ArticleQueryDto.builder().pageSize(pageSize).pageNum(pageNum).status(1).build();
        IPage<ArticleListVo> articles = articleService.getArticleList(queryDto);
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
            @RequestParam Integer limit) {
        List<PopularArticleVo> articles = articleService.getPopularArticles(limit);
        return Result.ok(articles);
    }
    
    @Operation(summary = "发布文章")
    @PutMapping
    public Result<ArticleDetailVo> publishArticle(@RequestParam @Valid Long id) {
        ArticleDetailVo articleDetailVo = articleService.publishArticle(id);
        return Result.ok(articleDetailVo);
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
    public Result<IPage<MyArticleVo>> getMyArticles(@Parameter(description = "页码")@RequestParam Integer pageNum,
                                                    @Parameter(description = "每页大小")@RequestParam Integer pageSize,
                                                    @Parameter(description = "文章状态：0 草稿，1 已发布")@RequestParam Integer status) {
        ArticleQueryDto queryDto = ArticleQueryDto.builder().pageSize(pageSize).pageNum(pageNum).status(status).build();
        IPage<MyArticleVo> articles = articleService.getMyArticles(queryDto);
        return Result.ok(articles);
    }
}