package com.xiaoxin.blog.web.app.service;

import com.xiaoxin.blog.web.app.dto.RecommendClickDto;
import com.xiaoxin.blog.web.app.vo.RecommendArticleVo;
import com.xiaoxin.blog.web.app.vo.RecommendCategoryVo;
import com.xiaoxin.blog.web.app.vo.RecommendTagVo;
import com.xiaoxin.blog.web.app.vo.RecommendUserVo;

import java.util.List;

public interface RecommendService{

    List<RecommendArticleVo> getRecommendArticles(Long articleId, Integer limit);

    List<RecommendArticleVo> getPersonalizedRecommend(Integer limit);

    List<RecommendUserVo> getRecommendUsers(Integer limit);

    List<RecommendArticleVo> getRelatedArticles(Long articleId, Integer limit);

    List<RecommendCategoryVo> getRecommendCategories(Integer limit);

    List<RecommendTagVo> getRecommendTags(Integer limit);

    void recordRecommendClick(RecommendClickDto clickDto);
}
