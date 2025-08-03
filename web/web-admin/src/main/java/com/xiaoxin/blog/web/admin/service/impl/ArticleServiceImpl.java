package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.web.admin.mapper.ArticleMapper;
import com.xiaoxin.blog.web.admin.service.ArticleService;
import org.springframework.stereotype.Service;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

}




