package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.dto.UpdateArticleDto;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.mapper.pojo.ArticleIdTagName;
import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.vo.ArticleDetailVo;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.MyArticleVo;
import com.xiaoxin.blog.web.app.vo.PopularArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public IPage<ArticleListVo> getArticleList(ArticleQueryDto queryDto)
    {
        IPage<ArticleListVo> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        return articleMapper.selectPagedArticles(page, queryDto);
    }

    @Override
    public ArticleDetailVo getArticleDetail(Long id)
    {
        Article article = articleMapper.selectById(id);
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
        BeanUtils.copyProperties(article, articleDetailVo);
        List<ArticleIdTagName> articleIdTagNames = articleMapper.selectTagNamesByArticleIds(Collections.singletonList(id));
        Map<Long,List<String>> tagNames = articleIdTagNames.stream()
                                                           .collect(Collectors.groupingBy(ArticleIdTagName::getArticleId,
                                                                   Collectors.mapping(ArticleIdTagName::getTagName,
                                                                           Collectors.toList())));
        articleDetailVo.setTagsName(tagNames.getOrDefault(id, Collections.emptyList()));
        return articleDetailVo;
    }

    @Override
    public List<PopularArticleVo> getPopularArticles(Integer limit)
    {
        int wArticle = 1;
        int wComment = 3;
        int wLike = 2;
        return articleMapper.getPopularArticles(wArticle, wComment, wLike,limit);
    }

    @Override
    public ArticleDetailVo publishArticle(Long id)
    {
        Long userId = LoginUserHolder.get().getUserId();
        Article article = articleMapper.selectById(id);
        if(article == null) throw new BlogException(ResultCodeEnum.APP_DATA_NOT_EXIST);
        if(!Objects.equals(article.getCreateBy(), userId)) throw new BlogException(ResultCodeEnum.APP_NO_AUTH);
        article.setStatus(1);
        articleMapper.updateById(article);
        return getArticleDetail(id);
    }



    @Override
    public void updateUserArticle(Long id, UpdateArticleDto updateDto)
    {
        Long userId = LoginUserHolder.get().getUserId();
        Article article = articleMapper.selectById(id);
        if(article == null) throw new BlogException(ResultCodeEnum.APP_DATA_NOT_EXIST);
        if(!Objects.equals(article.getCreateBy(), userId)) throw new BlogException(ResultCodeEnum.APP_NO_AUTH);
        BeanUtils.copyProperties(updateDto, article);
        articleMapper.updateById(article);
    }

    @Override
    public void deleteUserArticle(Long id)
    {
        Long userId = LoginUserHolder.get().getUserId();
        Article article = articleMapper.selectById(id);
        if(article == null) throw new BlogException(ResultCodeEnum.APP_DATA_NOT_EXIST);
        if(!Objects.equals(article.getCreateBy(), userId)) throw new BlogException(ResultCodeEnum.APP_NO_AUTH);
        articleMapper.deleteById(id);
    }

    @Override
    public IPage<MyArticleVo> getMyArticles(ArticleQueryDto queryDto)
    {
        Long userId = LoginUserHolder.get().getUserId();
        IPage<MyArticleVo> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        return articleMapper.getMyArticles(page, queryDto,userId);
    }



}




