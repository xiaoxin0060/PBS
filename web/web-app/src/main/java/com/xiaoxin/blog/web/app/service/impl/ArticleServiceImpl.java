package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.enums.PopularType;
import com.xiaoxin.blog.web.app.dto.*;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.vo.ArticleDetailVo;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.MyArticleVo;
import com.xiaoxin.blog.web.app.vo.PopularArticleVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

    @Override
    public IPage<ArticleListVo> getArticleList(ArticleQueryDto queryDto)
    {
        return null;
    }

    @Override
    public ArticleDetailVo getArticleDetail(Long id)
    {
        return null;
    }

    @Override
    public List<PopularArticleVo> getPopularArticles(PopularType type, Integer days, Integer limit)
    {
        return List.of();
    }

    @Override
    public Long publishArticle(PublishArticleDto publishDto)
    {
        return 0L;
    }

    @Override
    public void updateUserArticle(Long id, UpdateArticleDto updateDto)
    {

    }

    @Override
    public void deleteUserArticle(Long id)
    {

    }

    @Override
    public IPage<MyArticleVo> getMyArticles(MyArticleQueryDto queryDto)
    {
        return null;
    }

    @Override
    public IPage<ArticleListVo> getArticlesByCategory(Long categoryId, PageQueryDto queryDto)
    {
        return null;
    }

    @Override
    public IPage<ArticleListVo> getArticlesByTag(Long tagId, PageQueryDto queryDto)
    {
        return null;
    }
}




