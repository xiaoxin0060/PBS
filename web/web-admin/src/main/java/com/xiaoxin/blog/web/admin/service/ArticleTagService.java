package com.xiaoxin.blog.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.ArticleTag;
import com.xiaoxin.blog.web.admin.vo.TagVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【article_tag(文章标签关联表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface ArticleTagService extends IService<ArticleTag> {

    List<TagVo> getTagsByArticleId(Long articleId);
}
