package com.xiaoxin.blog.web.app.controller.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.service.CategoryService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.CategoryDetailVo;
import com.xiaoxin.blog.web.app.vo.CategoryStatsVo;
import com.xiaoxin.blog.web.app.vo.CategoryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "文章分类")
@RequestMapping("/app/categories")
@RestController
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    
    @Operation(summary = "获取所有分类")
    @GetMapping
    public Result<List<CategoryVo>> getAllCategories() {
        List<CategoryVo> categories = categoryService.getAllActiveCategories();
        return Result.ok(categories);
    }
    
    @Operation(summary = "获取热门分类")
    @GetMapping("/hot")
    public Result<List<CategoryVo>> getHotCategories(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<CategoryVo> categories = categoryService.getHotCategories(limit);
        return Result.ok(categories);
    }
    
    @Operation(summary = "获取分类详情")
    @GetMapping("/{id}")
    public Result<CategoryDetailVo> getCategoryDetail(@PathVariable Long id) {
        CategoryDetailVo category = categoryService.getCategoryDetail(id);
        return Result.ok(category);
    }
    
    @Operation(summary = "获取分类下的文章")
    @GetMapping("/{id}/articles")
    public Result<IPage<ArticleListVo>> getCategoryArticles(
            @PathVariable Long id, 
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "createTime") String sort) {
        
        ArticleQueryDto queryDto = ArticleQueryDto.builder()
                                                  .categoryId(id)
                                                  .page(page)
                                                  .size(size)
                                                  .sort(sort)
                                                  .build();

        IPage<ArticleListVo> articles = articleService.getArticleList(queryDto);
        return Result.ok(articles);
    }
    
    @Operation(summary = "获取分类统计信息")
    @GetMapping("/{id}/stats")
    public Result<CategoryStatsVo> getCategoryStats(@PathVariable Long id) {
        CategoryStatsVo stats = categoryService.getCategoryStats(id);
        return Result.ok(stats);
    }
}