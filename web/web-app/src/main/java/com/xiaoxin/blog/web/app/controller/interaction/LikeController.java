package com.xiaoxin.blog.web.app.controller.interaction;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "点赞功能")
@RequestMapping("/app/likes")
@RestController
public class LikeController {
    
    @Autowired
    private LikeService likeService;
    
    @Operation(summary = "文章点赞/取消")
    @PostMapping("/article/{articleId}")
    public Result<LikeStatusVo> toggleArticleLike(@PathVariable Long articleId) {
        LikeStatusVo status = likeService.toggleArticleLike(articleId);
        return Result.ok(status);
    }
    
    @Operation(summary = "评论点赞/取消")
    @PostMapping("/comment/{commentId}")
    public Result<LikeStatusVo> toggleCommentLike(@PathVariable Long commentId) {
        LikeStatusVo status = likeService.toggleCommentLike(commentId);
        return Result.ok(status);
    }
    
    @Operation(summary = "获取点赞状态")
    @GetMapping("/status")
    public Result<LikeBatchStatusVo> getLikeStatus(LikeStatusQueryDto queryDto) {
        LikeBatchStatusVo status = likeService.getBatchLikeStatus(queryDto);
        return Result.ok(status);
    }
    
    @Operation(summary = "获取我点赞的文章")
    @GetMapping("/articles")
    public Result<PageResult<LikedArticleVo>> getMyLikedArticles(PageQueryDto queryDto) {
        PageResult<LikedArticleVo> articles = likeService.getMyLikedArticles(queryDto);
        return Result.ok(articles);
    }
}