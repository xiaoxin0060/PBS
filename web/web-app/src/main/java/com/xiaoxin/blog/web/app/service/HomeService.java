package com.xiaoxin.blog.web.app.service;

import com.xiaoxin.blog.web.app.vo.BannerVo;
import com.xiaoxin.blog.web.app.vo.HomeDataVo;
import com.xiaoxin.blog.web.app.vo.RecommendArticleVo;

import java.util.List;

public interface HomeService{
    List<BannerVo> getBanners();

    List<RecommendArticleVo> getRecommendArticles(Long articleId, Integer limit);

    HomeDataVo getHomeData();
}
