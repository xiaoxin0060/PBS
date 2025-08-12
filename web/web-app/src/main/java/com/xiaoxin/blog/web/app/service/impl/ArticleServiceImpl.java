package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import org.springframework.stereotype.Service;

/**
* @author 小新
* @description 针对表【article(文章表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

    @Override
    public IPage<ArticleListVo> getArticleList(ArticleQueryDto queryDto)
    {
        return null;
    }
}




