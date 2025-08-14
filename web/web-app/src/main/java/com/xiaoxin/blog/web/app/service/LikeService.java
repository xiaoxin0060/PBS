package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.vo.LikeStatusVo;
import com.xiaoxin.blog.web.app.vo.LikedArticleVo;

import java.util.Map;

public interface LikeService{
    LikeStatusVo toggleArticleLike(Long articleId);

    Map<Long,Boolean> getBatchLikeStatus();

    IPage<LikedArticleVo> getMyLikedArticles(Integer pageNum, Integer pageSize);
}
