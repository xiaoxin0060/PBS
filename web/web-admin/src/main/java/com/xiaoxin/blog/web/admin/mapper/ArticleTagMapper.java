package com.xiaoxin.blog.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.blog.model.entity.ArticleTag;
import com.xiaoxin.blog.web.admin.vo.TagVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【article_tag(文章标签关联表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.ArticleTag
*/

public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<TagVo> getTagsByArticleId(Long articleId);

    void batchAddTags(Long articleId, List<Long> tagIds);

    void restoreArticleTag(Long articleId, Long tagId);
}




