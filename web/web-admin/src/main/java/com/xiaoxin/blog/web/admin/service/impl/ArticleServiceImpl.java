package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.model.entity.ArticleTag;
import com.xiaoxin.blog.model.entity.Category;
import com.xiaoxin.blog.web.admin.mapper.ArticleMapper;
import com.xiaoxin.blog.web.admin.mapper.ArticleTagMapper;
import com.xiaoxin.blog.web.admin.mapper.CategoryMapper;
import com.xiaoxin.blog.web.admin.mapper.TagMapper;
import com.xiaoxin.blog.web.admin.service.ArticleService;
import com.xiaoxin.blog.web.admin.service.TagService;
import com.xiaoxin.blog.web.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 小新
 * @description 针对表【article(文章表)】的数据库操作Service实现
 * @createDate 2025-08-04 00:14:52
 */

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public IPage<ArticleVo> getArticles(IPage<ArticleVo> page, ArticleQueryVo articleQueryVo){
        //1.先分页查询符合查询条件的文章
        //(1)selectPage分页查询需要一个查询条件，所以需要用LambdaQueryWrapper，又因为查询条件涉及多表查询构造比较复杂,所以专门搞个函数构造查询条件
        LambdaQueryWrapper<Article> queryWrapper = buildWrapper(articleQueryVo);
        Page<Article> articlePage = articleMapper.selectPage(new Page<>(page.getCurrent(), page.getSize()), queryWrapper);
        if(articlePage.getRecords() == null || articlePage
                .getRecords()
                .isEmpty()) return new Page<>();
        //2.查询和封装TagVo
        //拿到所有文章Id
        List<Long> articleIds = articlePage
                .getRecords()
                .stream()
                .map(Article::getId)
                .collect(Collectors.toList());
        //用来查询出所有文章关联的TagVo
        List<TagVo> tagVos = tagMapper.listByArticleIds(articleIds);
        //再按照文章Id进行分组
        Map<Long,List<TagVo>> tagVoMap = tagVos
                .stream()
                .collect(Collectors.groupingBy(TagVo::getArticleId));
        //3.查询和封装CategoryVo
        //拿到所有分类id并去重然后查出id对应的分类信息封装成CategoryVo
        Set<Long> categoryVoSet = articlePage
                .getRecords()
                .stream()
                .map(Article::getCategoryId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        List<Category> categoryList = categoryMapper.selectBatchIds(categoryVoSet);
        Map<Long,CategoryVo> categoryVoMap = categoryList
                .stream()
                .collect(Collectors.toMap(Category::getId, cat -> {
                    return new CategoryVo(cat.getId(), cat.getName());
                }));
        //4.组装ArticleVo
        List<ArticleVo> articleVoList = articlePage
                .getRecords()
                .stream()
                .map(article -> {
                    ArticleVo articleVo = new ArticleVo();
                    BeanUtils.copyProperties(article, articleVo);
                    articleVo.setCategory(categoryVoMap.get(article.getCategoryId()));
                    articleVo.setTags(tagVoMap.getOrDefault(article.getId(), Collections.emptyList()));
                    return articleVo;
                })
                .collect(Collectors.toList());
        //5.组装IPage<ArticleVo>
        Page<ArticleVo> voPage = new Page<>(articlePage.getCurrent(), articlePage.getSize(), articlePage.getTotal());
        voPage.setRecords(articleVoList);
        return voPage;
    }

    @Override
    public ArticleDetailVo getArticleById(Long id){
        Article article = articleMapper.selectById(id);
        if(article == null) throw new BlogException(ResultCodeEnum.ARTICLE_NOT_EXIST);
        List<TagVo> tags = articleTagMapper.getTagsByArticleId(id);
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
        BeanUtils.copyProperties(article, articleDetailVo);
        articleDetailVo.setTags(tags);
        Category category = categoryMapper.selectById(article.getCategoryId());
        articleDetailVo.setCategory(new CategoryVo(category.getId(), category.getName()));
        return articleDetailVo;
    }

    @Override
    public void restoreArticle(Long id){
        articleMapper.restoreArticle(id);
    }

    @Override
    public void addTagsToArticle(Long id, List<Long> tagIds){
        if(tagIds == null || tagIds.isEmpty()){
            return;
        }
        List<ArticleTag> articleTags = tagIds
                .stream()
                .map(tagId -> new ArticleTag(id, tagId))
                .collect(Collectors.toList());
        try{
            articleTagMapper.insert(articleTags);
        }catch(Throwable e){
            throw new BlogException(ResultCodeEnum.DATA_EXIST);
        }
    }

    @Override
    public void removeTagsFromArticle(Long id, List<Long> tagIds){
        if(tagIds == null || tagIds.isEmpty()){
            return;
        }
        LambdaQueryWrapper<ArticleTag> updateWrapper = new LambdaQueryWrapper<ArticleTag>()
                .eq(ArticleTag::getArticleId, id)
                .in(ArticleTag::getTagId, tagIds);
        //配置了逻辑删除@TableLogic，这里delete方法会自动将逻辑删除字段置为1
        articleTagMapper.delete(updateWrapper);
    }

    private LambdaQueryWrapper<Article> buildWrapper(ArticleQueryVo queryVo){
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if(queryVo != null && queryVo.getTagId() != null){
            List<Long> articleIdsByTags = getArticleIdsByTags(queryVo.getTagId());
            if(articleIdsByTags.isEmpty()){
                queryWrapper.eq(Article::getId, -1L); // 强制空结果
                return queryWrapper; // 早返回
            }
            queryWrapper.in(Article::getId, articleIdsByTags);
        }
        if(queryVo != null){
            queryWrapper
                    .like(StringUtils.isNotBlank(queryVo.getTitle()), Article::getTitle, queryVo.getTitle())
                    .eq(queryVo.getUserId() != null, Article::getUserId, queryVo.getUserId())
                    .eq(queryVo.getCategoryId() != null, Article::getCategoryId, queryVo.getCategoryId());
            //Wrapper的类型Article，又是因为Article没有tagId字段，所以Wrapper的构造要转换为用Article的Id进行查询
        }
        return queryWrapper;
    }

    // 新加：私有方法，实现“且”关系（文章必须包含所有 tagIds）
    private List<Long> getArticleIdsByTags(List<Long> tagIds){
        //直接查出包含任意传入标签的文章然后用stream过滤出包含所有tagIds的文章
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<ArticleTag>()
                .in(ArticleTag::getTagId, tagIds)
                .select(ArticleTag::getArticleId, ArticleTag::getTagId);
        List<ArticleTag> articleTags = articleTagMapper.selectList(queryWrapper);
        //先分组
        Map<Long,Set<Long>> articleTagsMap = articleTags
                .stream()
                .collect(Collectors.groupingBy(ArticleTag::getArticleId, Collectors.mapping(ArticleTag::getTagId, Collectors.toSet())));
        //再过滤
        return articleTagsMap
                .entrySet()
                .stream()
                .filter(ent -> ent
                        .getValue()
                        .containsAll(tagIds))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }
}




