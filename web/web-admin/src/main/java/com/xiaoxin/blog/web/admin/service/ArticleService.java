package com.xiaoxin.blog.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.enums.PopularType;
import com.xiaoxin.blog.web.admin.vo.ArticleDetailVo;
import com.xiaoxin.blog.web.admin.vo.ArticleQueryVo;
import com.xiaoxin.blog.web.admin.vo.ArticleVo;
import com.xiaoxin.blog.web.admin.vo.PopularArticleVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface ArticleService extends IService<Article> {

    IPage<ArticleVo> getArticles(IPage<ArticleVo> page, ArticleQueryVo articleQueryVo);

    ArticleDetailVo getArticleById(Long id);

    void restoreArticle(Long id);

    void addTagsToArticle(Long id, List<Long> tagIds);

    void removeTagsFromArticle(Long id, List<Long> tagIds);

    List<PopularArticleVo> getPopularArticles(PopularType type, Integer days, Integer limit);
}
