package com.xiaoxin.blog.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.enums.PopularType;
import com.xiaoxin.blog.web.admin.vo.PopularArticleVo;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.Article
*/

public interface ArticleMapper extends BaseMapper<Article> {

    void restoreArticle(Long id);


    List<PopularArticleVo> getPopularArticles(PopularType type, LocalDateTime startTime, Integer limit);
}




