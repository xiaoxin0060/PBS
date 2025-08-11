package com.xiaoxin.blog.web.app.controller.discover;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<PageResult<ArticleListVo>> searchArticles(ArticleSearchDto searchDto) {
        PageResult<ArticleListVo> articles = searchService.searchArticles(searchDto);
        return Result.ok(articles);
    }
    
    @Operation(summary = "搜索建议")
    @GetMapping("/suggestions")
    public Result<List<String>> getSearchSuggestions(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "10") Integer limit) {
        List<String> suggestions = searchService.getSearchSuggestions(keyword, limit);
        return Result.ok(suggestions);
    }
    
    @Operation(summary = "热门搜索")
    @GetMapping("/hot-keywords")
    public Result<List<String>> getHotKeywords(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<String> keywords = searchService.getHotKeywords(limit);
        return Result.ok(keywords);
    }
}