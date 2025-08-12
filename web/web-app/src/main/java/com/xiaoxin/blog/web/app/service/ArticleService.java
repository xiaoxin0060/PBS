package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface ArticleService extends IService<Article> {

    IPage<ArticleListVo> getArticleList(ArticleQueryDto queryDto);
}
