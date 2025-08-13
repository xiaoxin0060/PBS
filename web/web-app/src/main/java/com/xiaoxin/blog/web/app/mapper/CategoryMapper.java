package com.xiaoxin.blog.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.blog.model.entity.Category;
import com.xiaoxin.blog.web.app.vo.CategoryVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【category(分类表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.Category
*/
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> selectHotCategories(Integer limit);

    List<CategoryVo> selectAllWithArticleCount();

}




