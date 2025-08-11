package com.xiaoxin.blog.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.blog.model.entity.Article;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.Article
*/

public interface ArticleMapper extends BaseMapper<Article> {

    void restoreArticle(Long id);
}




