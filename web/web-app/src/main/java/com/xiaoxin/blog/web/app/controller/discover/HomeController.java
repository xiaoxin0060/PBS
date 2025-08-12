package com.xiaoxin.blog.web.app.controller.discover;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.service.HomeService;
import com.xiaoxin.blog.web.app.vo.BannerVo;
import com.xiaoxin.blog.web.app.vo.HomeDataVo;
import com.xiaoxin.blog.web.app.vo.RecommendArticleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "首页发现")
@RequestMapping("/app/discover")
@RestController
public class HomeController {
    
    @Autowired
    private HomeService homeService;
    
    @Operation(summary = "获取首页数据")
    @GetMapping("/home")
    public Result<HomeDataVo> getHomeData() {
        HomeDataVo homeData = homeService.getHomeData();
        return Result.ok(homeData);
    }
    
    @Operation(summary = "获取推荐文章")
    @GetMapping("/recommend")
    public Result<List<RecommendArticleVo>> getRecommendArticles(
            @RequestParam(required = false) Long articleId,
            @RequestParam(defaultValue = "5") Integer limit) {
        List<RecommendArticleVo> articles = homeService.getRecommendArticles(articleId, limit);
        return Result.ok(articles);
    }
    
    @Operation(summary = "获取轮播图")
    @GetMapping("/banners")
    public Result<List<BannerVo>> getBanners() {
        List<BannerVo> banners = homeService.getBanners();
        return Result.ok(banners);
    }
}