package com.xiaoxin.blog.web.app.controller.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.PageQueryDto;
import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.service.CategoryService;
import com.xiaoxin.blog.web.app.service.TagService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.CategoryVo;
import com.xiaoxin.blog.web.app.vo.TagVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "分类标签")
@RequestMapping("/app")
@RestController
public class CategoryTagController {
    
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleService articleService;
    
    @Operation(summary = "获取分类列表")
    @GetMapping("/categories")
    public Result<List<CategoryVo>> getCategories() {
        List<CategoryVo> categories = categoryService.getAllCategories();
        return Result.ok(categories);
    }
    
    @Operation(summary = "获取分类文章")
    @GetMapping("/categories/{categoryId}/articles")
    public Result<IPage<ArticleListVo>> getCategoryArticles(
            @PathVariable Long categoryId, PageQueryDto queryDto) {
        IPage<ArticleListVo> articles = articleService.getArticlesByCategory(categoryId, queryDto);
        return Result.ok(articles);
    }
    
    @Operation(summary = "获取标签列表")
    @GetMapping("/tags")
    public Result<List<TagVo>> getTags(@RequestParam(defaultValue = "false") Boolean hot,
                                       @RequestParam(defaultValue = "50") Integer limit) {
        List<TagVo> tags = tagService.getTags(hot, limit);
        return Result.ok(tags);
    }
    
    @Operation(summary = "获取标签文章")
    @GetMapping("/tags/{tagId}/articles")
    public Result<IPage<ArticleListVo>> getTagArticles(
            @PathVariable Long tagId, PageQueryDto queryDto) {
        IPage<ArticleListVo> articles = articleService.getArticlesByTag(tagId, queryDto);
        return Result.ok(articles);
    }
}