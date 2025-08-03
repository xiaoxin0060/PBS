package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Category;
import com.xiaoxin.blog.web.admin.mapper.CategoryMapper;
import com.xiaoxin.blog.web.admin.service.CategoryService;
import org.springframework.stereotype.Service;

/**
* @author 小新
* @description 针对表【category(分类表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

}




