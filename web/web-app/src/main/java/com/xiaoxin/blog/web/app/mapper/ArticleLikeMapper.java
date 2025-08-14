package com.xiaoxin.blog.web.app.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.model.entity.ArticleLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.blog.web.app.vo.LikedArticleVo;

/**
* @author 小新
* @description 针对表【article_like(文章点赞关系)】的数据库操作Mapper
* @createDate 2025-08-14 21:47:58
* @Entity com.xiaoxin.blog.model.entity.ArticleLike
*/
public interface ArticleLikeMapper extends BaseMapper<ArticleLike> {

    IPage<LikedArticleVo> getMyLikedArticles(IPage<LikedArticleVo> page, Long userId);
}




