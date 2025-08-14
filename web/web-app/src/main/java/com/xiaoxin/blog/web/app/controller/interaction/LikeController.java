package com.xiaoxin.blog.web.app.controller.interaction;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.service.LikeService;
import com.xiaoxin.blog.web.app.vo.LikeStatusVo;
import com.xiaoxin.blog.web.app.vo.LikedArticleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "点赞功能")
@RequestMapping("/app/likes")
@RestController
public class LikeController {
    
    @Autowired
    private LikeService likeService;
    
    @Operation(summary = "文章点赞/取消")
    @PutMapping("/article/{articleId}")
    public Result<LikeStatusVo> toggleArticleLike(@Parameter(description = "点赞文章的ID") @PathVariable Long articleId) {
        LikeStatusVo status = likeService.toggleArticleLike(articleId);
        return Result.ok(status);
    }
    
    @Operation(summary = "获取点赞状态")
    @GetMapping("/status")
    public Result<Map<Long, Boolean>> getLikeStatus() {
        Map<Long, Boolean> status = likeService.getBatchLikeStatus();
        return Result.ok(status);
    }
    
    @Operation(summary = "获取我点赞的文章")
    @GetMapping("/articles")
    public Result<IPage<LikedArticleVo>> getMyLikedArticles(@RequestParam Integer pageNum,
                                                            @RequestParam Integer pageSize) {
        IPage<LikedArticleVo> articles = likeService.getMyLikedArticles(pageNum,pageSize);
        return Result.ok(articles);
    }
}