package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Tag;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.mapper.TagMapper;
import com.xiaoxin.blog.web.app.mapper.pojo.ArticleIdTagName;
import com.xiaoxin.blog.web.app.service.TagService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 小新
* @description 针对表【tag(标签表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<TagVo> getHotTags(Integer limit)
    {
        // 权重：你可以改成从配置读取
        int wArticle = 1;
        int wComment = 3;
        int wLike = 2;
        // 查询
        List<TagVo> list = tagMapper.getHotTags(limit, wArticle, wComment, wLike);
        return list;
    }

    @Override
    public List<TagVo> getAllTags()
    {
        return tagMapper.getAllTags();
    }

    @Override
    public IPage<ArticleListVo> getTagArticles(ArticleQueryDto queryDto)
    {
        IPage<ArticleListVo> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        // 分页查询文章
        IPage<ArticleListVo> articles = articleMapper.selectPagedArticles(page, queryDto);

        // 如果没有记录，直接返回
        List<ArticleListVo> records = articles.getRecords();
        if (records == null || records.isEmpty()) {
            return articles;
        }

        // 收集文章ID
        List<Long> articleIds = records.stream()
                                       .map(ArticleListVo::getId)
                                       .filter(Objects::nonNull)
                                       .toList();

        if (articleIds.isEmpty()) {
            return articles;
        }

        // 批量查询标签并分组
        List<ArticleIdTagName> pairs = articleMapper.selectTagNamesByArticleIds(articleIds);
        Map<Long, List<String>> tagMap = pairs == null ? Collections.emptyMap()
                : pairs.stream().collect(Collectors.groupingBy(
                ArticleIdTagName::getArticleId,
                Collectors.mapping(ArticleIdTagName::getTagName, Collectors.toList())
        ));

        // 回填标签
        records.forEach(a -> a.setTagsName(tagMap.getOrDefault(a.getId(), Collections.emptyList())));

        return articles;

    }
}




