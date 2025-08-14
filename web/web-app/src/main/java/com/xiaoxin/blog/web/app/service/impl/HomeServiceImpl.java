package com.xiaoxin.blog.web.app.service.impl;

import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.service.CategoryService;
import com.xiaoxin.blog.web.app.service.HomeService;
import com.xiaoxin.blog.web.app.service.TagService;
import com.xiaoxin.blog.web.app.vo.CategoryVo;
import com.xiaoxin.blog.web.app.vo.HomeDataVo;
import com.xiaoxin.blog.web.app.vo.PopularArticleVo;
import com.xiaoxin.blog.web.app.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService{
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;


    @Override
    public HomeDataVo getHomeData()
    {
        List<PopularArticleVo> popularArticles = articleService.getPopularArticles(5);
        List<TagVo> popularTags = tagService.getHotTags(5);
        List<CategoryVo> popularCategories = categoryService.getHotCategories(5);
        HomeDataVo homeDataVo = new HomeDataVo();
        homeDataVo.setPopularArticles(popularArticles);
        homeDataVo.setPopularTags(popularTags);
        homeDataVo.setPopularCategories(popularCategories);
        return homeDataVo;
    }
}
