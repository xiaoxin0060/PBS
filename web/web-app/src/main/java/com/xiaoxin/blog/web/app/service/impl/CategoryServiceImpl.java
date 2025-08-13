package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Category;
import com.xiaoxin.blog.web.app.dto.CategoryArticleQueryDto;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.mapper.CategoryMapper;
import com.xiaoxin.blog.web.app.mapper.TagMapper;
import com.xiaoxin.blog.web.app.service.CategoryService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.CategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小新
* @description 针对表【category(分类表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<CategoryVo> getAllCategories() {
        return categoryMapper.selectAllWithArticleCount();
    }

    @Override
    public List<CategoryVo> getHotCategories(Integer limit) {
        int topN = (limit == null || limit <= 0) ? 10 : limit;
        return categoryMapper.selectHotCategories(topN);
    }

    @Override
    public IPage<ArticleListVo> getCategoryArticles(CategoryArticleQueryDto queryDto)
    {
        IPage<ArticleListVo> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        return articleMapper.getCategoryArticles(page, queryDto);
    }





}




