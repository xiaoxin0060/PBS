package com.xiaoxin.blog.web.app.service.impl;

import com.xiaoxin.blog.web.app.service.HomeService;
import com.xiaoxin.blog.web.app.vo.BannerVo;
import com.xiaoxin.blog.web.app.vo.HomeDataVo;
import com.xiaoxin.blog.web.app.vo.RecommendArticleVo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeServiceImpl implements HomeService{
    @Override
    public List<BannerVo> getBanners()
    {
        return List.of();
    }

    @Override
    public List<RecommendArticleVo> getRecommendArticles(Long articleId, Integer limit)
    {
        return List.of();
    }

    @Override
    public HomeDataVo getHomeData()
    {
        return null;
    }
}
