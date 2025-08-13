package com.xiaoxin.blog.web.app.controller.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.CategoryArticleQueryDto;
import com.xiaoxin.blog.web.app.service.CategoryService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
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

    @Operation(summary = "获取所有分类")
    @GetMapping("/all")
    public Result<List<CategoryVo>> getAllCategories() {
        return Result.ok(categoryService.getAllCategories());
    }

    @Operation(summary = "获取热门分类")
    @GetMapping("/hot")
    public Result<List<CategoryVo>> getHotCategories(@RequestParam(required = false) Integer limit) {
        return Result.ok(categoryService.getHotCategories(limit));
    }

    @Operation(summary = "获取分类下的文章")
    @GetMapping("/{id}/articles")
    public Result<IPage<ArticleListVo>> getCategoryArticles(@PathVariable Long id,
                                                           @RequestParam Integer status,
                                                           @RequestParam Integer pageNum,
                                                           @RequestParam Integer pageSize) {
        CategoryArticleQueryDto queryDto = CategoryArticleQueryDto.builder().categoryId(id).status(status).pageNum(pageNum)
                                                                  .pageSize(pageSize).build();
        return Result.ok(categoryService.getCategoryArticles(queryDto));
    }

}