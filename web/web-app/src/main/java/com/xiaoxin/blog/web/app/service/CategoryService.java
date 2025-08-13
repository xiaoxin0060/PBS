package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.Category;
import com.xiaoxin.blog.web.app.dto.CategoryArticleQueryDto;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.CategoryVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【category(分类表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface CategoryService extends IService<Category> {

    List<CategoryVo> getHotCategories(Integer limit);

    List<CategoryVo> getAllCategories();


    IPage<ArticleListVo> getCategoryArticles(CategoryArticleQueryDto queryDto);
}
