package com.xiaoxin.blog.web.app.controller.interaction;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "收藏功能")
@RequestMapping("/app/favorites")
@RestController
public class FavoriteController {
    
    @Autowired
    private FavoriteService favoriteService;
    
    @Operation(summary = "收藏文章")
    @PostMapping("/article/{articleId}")
    public Result<FavoriteStatusVo> favoriteArticle(@PathVariable Long articleId) {
        FavoriteStatusVo status = favoriteService.toggleArticleFavorite(articleId);
        return Result.ok(status);
    }
    
    @Operation(summary = "获取收藏夹列表")
    @GetMapping("/folders")
    public Result<List<FavoriteFolderVo>> getFavoriteFolders() {
        List<FavoriteFolderVo> folders = favoriteService.getFavoriteFolders();
        return Result.ok(folders);
    }
    
    @Operation(summary = "创建收藏夹")
    @PostMapping("/folders")
    public Result<Long> createFavoriteFolder(@RequestBody @Valid CreateFavoriteFolderDto folderDto) {
        Long folderId = favoriteService.createFavoriteFolder(folderDto);
        return Result.ok(folderId);
    }
    
    @Operation(summary = "获取收藏的文章")
    @GetMapping("/articles")
    public Result<PageResult<FavoriteArticleVo>> getFavoriteArticles(
            @RequestParam(required = false) Long folderId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        
        PageResult<FavoriteArticleVo> articles = favoriteService.getFavoriteArticles(folderId, page, size);
        return Result.ok(articles);
    }
    
    @Operation(summary = "检查收藏状态")
    @GetMapping("/status")
    public Result<Map<Long, Boolean>> checkFavoriteStatus(@RequestParam List<Long> articleIds) {
        Map<Long, Boolean> statusMap = favoriteService.checkFavoriteStatus(articleIds);
        return Result.ok(statusMap);
    }
}