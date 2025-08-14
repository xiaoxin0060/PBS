package com.xiaoxin.blog.web.app.controller.discover;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.ArticleSearchDto;
import com.xiaoxin.blog.web.app.dto.GlobalSearchDto;
import com.xiaoxin.blog.web.app.service.SearchService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.GlobalSearchVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "搜索功能")
@RequestMapping("/app/search")
@RestController
public class SearchController {
    
    @Autowired
    private SearchService searchService;
    
    @Operation(summary = "全局搜索")
    @GetMapping
    public Result<GlobalSearchVo> globalSearch(GlobalSearchDto searchDto) {
        GlobalSearchVo result = searchService.globalSearch(searchDto);
        return Result.ok(result);
    }
    
    @Operation(summary = "文章搜索")
    @GetMapping("/articles")
    public Result<IPage<ArticleListVo>> searchArticles(ArticleSearchDto searchDto) {
        IPage<ArticleListVo> articles = searchService.searchArticles(searchDto);
        return Result.ok(articles);
    }
    

    
    @Operation(summary = "热门搜索")
    @GetMapping("/hot-keywords")
    public Result<List<String>> getHotKeywords(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<String> keywords = searchService.getHotKeywords(limit);
        return Result.ok(keywords);
    }
}