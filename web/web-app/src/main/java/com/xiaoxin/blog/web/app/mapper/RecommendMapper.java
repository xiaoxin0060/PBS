package com.xiaoxin.blog.web.app.mapper;

import com.xiaoxin.blog.web.app.vo.RecommendArticleVo;
import com.xiaoxin.blog.web.app.vo.RecommendCategoryVo;
import com.xiaoxin.blog.web.app.vo.RecommendTagVo;
import com.xiaoxin.blog.web.app.vo.RecommendUserVo;

import java.util.List;

public interface RecommendMapper{
    List<RecommendArticleVo> getPersonalizedRecommend(Long userId, Integer limit);

    List<RecommendUserVo> getRecommendUsers(Long userId, Integer limit);

    List<RecommendCategoryVo> getRecommendCategories(Long userId, Integer limit);

    List<RecommendTagVo> getRecommendTags(Long userId, Integer limit);
}
