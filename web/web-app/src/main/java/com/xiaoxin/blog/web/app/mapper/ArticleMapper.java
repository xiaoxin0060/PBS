package com.xiaoxin.blog.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.web.app.dto.CategoryArticleQueryDto;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<ArticleListVo> getCategoryArticles(IPage<ArticleListVo> page, CategoryArticleQueryDto queryDto);
}




