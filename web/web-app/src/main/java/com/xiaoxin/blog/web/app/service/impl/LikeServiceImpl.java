package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.LikeStatusQueryDto;
import com.xiaoxin.blog.web.app.dto.PageQueryDto;
import com.xiaoxin.blog.web.app.service.LikeService;
import com.xiaoxin.blog.web.app.vo.LikeBatchStatusVo;
import com.xiaoxin.blog.web.app.vo.LikeStatusVo;
import com.xiaoxin.blog.web.app.vo.LikedArticleVo;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{
    @Override
    public LikeStatusVo toggleArticleLike(Long articleId)
    {
        return null;
    }

    @Override
    public LikeStatusVo toggleCommentLike(Long commentId)
    {
        return null;
    }

    @Override
    public LikeBatchStatusVo getBatchLikeStatus(LikeStatusQueryDto queryDto)
    {
        return null;
    }

    @Override
    public IPage<LikedArticleVo> getMyLikedArticles(PageQueryDto queryDto)
    {
        return null;
    }
}
