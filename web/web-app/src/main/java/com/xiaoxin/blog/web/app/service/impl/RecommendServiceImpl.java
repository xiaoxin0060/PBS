package com.xiaoxin.blog.web.app.service.impl;

import com.xiaoxin.blog.web.app.dto.RecommendClickDto;
import com.xiaoxin.blog.web.app.service.RecommendService;
import com.xiaoxin.blog.web.app.vo.RecommendArticleVo;
import com.xiaoxin.blog.web.app.vo.RecommendCategoryVo;
import com.xiaoxin.blog.web.app.vo.RecommendTagVo;
import com.xiaoxin.blog.web.app.vo.RecommendUserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService{
    @Override
    public List<RecommendArticleVo> getRecommendArticles(Long articleId, Integer limit)
    {
        return List.of();
    }

    @Override
    public List<RecommendArticleVo> getPersonalizedRecommend(Integer limit)
    {
        return List.of();
    }

    @Override
    public List<RecommendUserVo> getRecommendUsers(Integer limit)
    {
        return List.of();
    }

    @Override
    public List<RecommendArticleVo> getRelatedArticles(Long articleId, Integer limit)
    {
        return List.of();
    }

    @Override
    public List<RecommendCategoryVo> getRecommendCategories(Integer limit)
    {
        return List.of();
    }

    @Override
    public List<RecommendTagVo> getRecommendTags(Integer limit)
    {
        return List.of();
    }

    @Override
    public void recordRecommendClick(RecommendClickDto clickDto)
    {

    }
}
