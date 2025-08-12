package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.enums.PopularType;
import com.xiaoxin.blog.web.app.dto.*;
import com.xiaoxin.blog.web.app.vo.ArticleDetailVo;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.MyArticleVo;
import com.xiaoxin.blog.web.app.vo.PopularArticleVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface ArticleService extends IService<Article> {

    IPage<ArticleListVo> getArticleList(ArticleQueryDto queryDto);

    ArticleDetailVo getArticleDetail(Long id);

    List<PopularArticleVo> getPopularArticles(PopularType type, Integer days, Integer limit);

    Long publishArticle(PublishArticleDto publishDto);

    void updateUserArticle(Long id, UpdateArticleDto updateDto);

    void deleteUserArticle(Long id);

    IPage<MyArticleVo> getMyArticles(MyArticleQueryDto queryDto);

    IPage<ArticleListVo> getArticlesByCategory(Long categoryId, PageQueryDto queryDto);

    IPage<ArticleListVo> getArticlesByTag(Long tagId, PageQueryDto queryDto);
}
