package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.entity.ArticleLike;
import com.xiaoxin.blog.web.app.mapper.ArticleLikeMapper;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.service.LikeService;
import com.xiaoxin.blog.web.app.vo.LikeStatusVo;
import com.xiaoxin.blog.web.app.vo.LikedArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService{
    @Autowired
    private ArticleLikeMapper articleLikeMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public LikeStatusVo toggleArticleLike(Long articleId)
    {
        // 校验文章
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            throw new BlogException(ResultCodeEnum.ARTICLE_NOT_EXIST);
        }
        Long userId = LoginUserHolder.get().getUserId();
        // 只用 Wrapper 查询是否已点赞
        LambdaQueryWrapper<ArticleLike> wrapper = new LambdaQueryWrapper<ArticleLike>().eq(ArticleLike::getArticleId, articleId)
                                                                                       .eq(ArticleLike::getUserId, userId);

        ArticleLike existed = articleLikeMapper.selectOne(wrapper);

        if(existed != null){
            // 已点赞 -> 取消点赞
            articleLikeMapper.delete(wrapper);

            // 计数-1，避免空指针与负数
            Integer likeCount = article.getLikeCount() == null ? 0 : article.getLikeCount();
            likeCount = Math.max(0, likeCount - 1);
            article.setLikeCount(likeCount);
            articleMapper.updateById(article);

            return new LikeStatusVo(false, likeCount);
        }else{
            // 未点赞 -> 新增关系
            ArticleLike record = new ArticleLike();
            record.setUserId(userId);
            record.setArticleId(articleId);
            record.setCreatedTime(new Date());
            articleLikeMapper.insert(record);

            // 计数+1
            Integer likeCount = article.getLikeCount() == null ? 0 : article.getLikeCount();
            likeCount = likeCount + 1;
            article.setLikeCount(likeCount);
            articleMapper.updateById(article);

            return new LikeStatusVo(true, likeCount);
        }
    }

    @Override
    public Map<Long,Boolean> getBatchLikeStatus()
    {
        Long userId = LoginUserHolder.get().getUserId();
        // 查出当前用户点赞过的所有文章ID，映射为 id -> true
        List<ArticleLike> list = articleLikeMapper.selectList(new LambdaQueryWrapper<ArticleLike>().eq(ArticleLike::getUserId, userId)
                                                                                                   .select(ArticleLike::getArticleId) // 只查 articleId 提升效率
                                                                                                   .orderByDesc(ArticleLike::getCreatedTime));
        return list.stream().collect(Collectors.toMap(ArticleLike::getArticleId, v -> true));
    }

    @Override
    public IPage<LikedArticleVo> getMyLikedArticles(Integer pageNum, Integer pageSize)
    {
        Long userId = LoginUserHolder.get().getUserId();
        IPage<LikedArticleVo> page = new Page<>(pageNum, pageSize);
        return articleLikeMapper.getMyLikedArticles(page, userId);

    }


}
