package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Category;
import com.xiaoxin.blog.web.app.mapper.CategoryMapper;
import com.xiaoxin.blog.web.app.service.CategoryService;
import com.xiaoxin.blog.web.app.vo.CategoryDetailVo;
import com.xiaoxin.blog.web.app.vo.CategoryStatsVo;
import com.xiaoxin.blog.web.app.vo.CategoryVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小新
* @description 针对表【category(分类表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

    @Override
    public List<CategoryVo> getAllActiveCategories()
    {
        return List.of();
    }

    @Override
    public List<CategoryVo> getHotCategories(Integer limit)
    {
        return List.of();
    }

    @Override
    public CategoryDetailVo getCategoryDetail(Long id)
    {
        return null;
    }

    @Override
    public CategoryStatsVo getCategoryStats(Long id)
    {
        return null;
    }
}




