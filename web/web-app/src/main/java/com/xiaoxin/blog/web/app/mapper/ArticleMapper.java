package com.xiaoxin.blog.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.mapper.pojo.ArticleIdTagName;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.DraftVo;
import com.xiaoxin.blog.web.app.vo.MyArticleVo;
import com.xiaoxin.blog.web.app.vo.PopularArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {
    IPage<ArticleListVo> getCategoryArticles(@Param("page")IPage<ArticleListVo> page, @Param("queryDto")ArticleQueryDto queryDto);
    // 一次性查询本页所有文章的标签名对，便于回填，避免 N+1
    List<ArticleIdTagName> selectTagNamesByArticleIds(@Param("articleIds")List<Long> articleIds);

    IPage<ArticleListVo> selectPagedArticles(@Param("page")IPage<ArticleListVo> page, @Param("queryDto")ArticleQueryDto queryDto);

    IPage<DraftVo> getDrafts(IPage<DraftVo> page, Long userId);

    List<PopularArticleVo> getPopularArticles(int wArticle, int wComment, int wLike, Integer limit);

    IPage<MyArticleVo> getMyArticles(IPage<MyArticleVo> page, ArticleQueryDto queryDto, Long userId);
}




