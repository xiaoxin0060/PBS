package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.ArticleTag;
import com.xiaoxin.blog.web.admin.mapper.ArticleTagMapper;
import com.xiaoxin.blog.web.admin.service.ArticleTagService;
import com.xiaoxin.blog.web.admin.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小新
 * @description 针对表【article_tag(文章标签关联表)】的数据库操作Service实现
 * @createDate 2025-08-04 00:14:52
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper,ArticleTag> implements ArticleTagService{
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<TagVo> getTagsByArticleId(Long articleId)
    {
        return articleTagMapper.getTagsByArticleId(articleId);
    }

    @Override
    public void batchAddTags(Long articleId, List<Long> tagIds)
    {
        articleTagMapper.batchAddTags(articleId, tagIds);
    }

    @Override
    public void restoreArticleTag(Long articleId, Long tagId)
    {
        articleTagMapper.restoreArticleTag(articleId, tagId);
    }
}




