package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.LikeStatusQueryDto;
import com.xiaoxin.blog.web.app.dto.PageQueryDto;
import com.xiaoxin.blog.web.app.vo.LikeBatchStatusVo;
import com.xiaoxin.blog.web.app.vo.LikeStatusVo;
import com.xiaoxin.blog.web.app.vo.LikedArticleVo;

public interface LikeService{
    LikeStatusVo toggleArticleLike(Long articleId);

    LikeStatusVo toggleCommentLike(Long commentId);

    LikeBatchStatusVo getBatchLikeStatus(LikeStatusQueryDto queryDto);

    IPage<LikedArticleVo> getMyLikedArticles(PageQueryDto queryDto);
}
