package com.xiaoxin.blog.web.app.service.impl;

import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.web.app.dto.RecommendClickDto;
import com.xiaoxin.blog.web.app.mapper.ClickLogMapper;
import com.xiaoxin.blog.web.app.mapper.RecommendMapper;
import com.xiaoxin.blog.web.app.service.RecommendService;
import com.xiaoxin.blog.web.app.vo.RecommendArticleVo;
import com.xiaoxin.blog.web.app.vo.RecommendCategoryVo;
import com.xiaoxin.blog.web.app.vo.RecommendTagVo;
import com.xiaoxin.blog.web.app.vo.RecommendUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService{

    @Autowired
    private ClickLogMapper clickLogMapper;
    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public List<RecommendArticleVo> getPersonalizedRecommend(Integer limit)
    {
        Long userId=LoginUserHolder.get().getUserId();
        return recommendMapper.getPersonalizedRecommend(userId, limit);
    }

    @Override
    public List<RecommendUserVo> getRecommendUsers(Integer limit)
    {
        Long userId=LoginUserHolder.get().getUserId();
        return recommendMapper.getRecommendUsers(userId, limit);
    }

    @Override
    public List<RecommendCategoryVo> getRecommendCategories(Integer limit)
    {
        Long userId=LoginUserHolder.get().getUserId();
        return recommendMapper.getRecommendCategories(userId, limit);
    }

    @Override
    public List<RecommendTagVo> getRecommendTags(Integer limit)
    {
        Long userId=LoginUserHolder.get().getUserId();
        return recommendMapper.getRecommendTags(userId, limit);
    }

    @Override
    public void recordRecommendClick(RecommendClickDto clickDto)
    {
        Long userId=LoginUserHolder.get().getUserId();
        clickLogMapper.recordRecommendClick(userId,clickDto);
    }
}
