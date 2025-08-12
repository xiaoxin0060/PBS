package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.Category;
import com.xiaoxin.blog.web.app.vo.CategoryDetailVo;
import com.xiaoxin.blog.web.app.vo.CategoryStatsVo;
import com.xiaoxin.blog.web.app.vo.CategoryVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【category(分类表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface CategoryService extends IService<Category> {

    List<CategoryVo> getAllActiveCategories();

    List<CategoryVo> getHotCategories(Integer limit);

    CategoryDetailVo getCategoryDetail(Long id);

    CategoryStatsVo getCategoryStats(Long id);

    List<CategoryVo> getAllCategories();
}
