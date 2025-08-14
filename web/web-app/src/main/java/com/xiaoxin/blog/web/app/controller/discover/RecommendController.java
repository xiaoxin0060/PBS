package com.xiaoxin.blog.web.app.controller.discover;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.enums.TargetType;
import com.xiaoxin.blog.web.app.dto.RecommendClickDto;
import com.xiaoxin.blog.web.app.service.RecommendService;
import com.xiaoxin.blog.web.app.vo.RecommendArticleVo;
import com.xiaoxin.blog.web.app.vo.RecommendCategoryVo;
import com.xiaoxin.blog.web.app.vo.RecommendTagVo;
import com.xiaoxin.blog.web.app.vo.RecommendUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "内容推荐")
@RequestMapping("/app/recommend")
@RestController
public class RecommendController {
    
    @Autowired
    private RecommendService recommendService;

    @Operation(summary = "获取个性化文章推荐")
    @GetMapping("/personalized")
    public Result<List<RecommendArticleVo>> getPersonalizedRecommend(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<RecommendArticleVo> articles = recommendService.getPersonalizedRecommend(limit);
        return Result.ok(articles);
    }
    //按照用户发布的文章点赞，评论数评判
    @Operation(summary = "获取推荐用户")
    @GetMapping("/users")
    public Result<List<RecommendUserVo>> getRecommendUsers(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<RecommendUserVo> users = recommendService.getRecommendUsers(limit);
        return Result.ok(users);
    }

    @Operation(summary = "获取推荐分类")
    @GetMapping("/categories")
    public Result<List<RecommendCategoryVo>> getRecommendCategories(
            @RequestParam(defaultValue = "8") Integer limit) {
        List<RecommendCategoryVo> categories = recommendService.getRecommendCategories(limit);
        return Result.ok(categories);
    }
    
    @Operation(summary = "获取推荐标签")
    @GetMapping("/tags")  
    public Result<List<RecommendTagVo>> getRecommendTags(
            @RequestParam(defaultValue = "15") Integer limit) {
        List<RecommendTagVo> tags = recommendService.getRecommendTags(limit);
        return Result.ok(tags);
    }
    
    @Operation(summary = "记录推荐点击")
    @PostMapping("/click")
    public Result<Void> recordRecommendClick(@RequestParam @Valid TargetType targetType,
                                             @RequestParam @Valid  Long targetId) {
        RecommendClickDto clickDto = RecommendClickDto.builder().targetType(targetType).targetId(targetId).build();
        recommendService.recordRecommendClick(clickDto);
        return Result.ok();
    }
}