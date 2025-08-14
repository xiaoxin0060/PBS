package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.ArticleLike;
import com.xiaoxin.blog.web.app.service.ArticleLikeService;
import com.xiaoxin.blog.web.app.mapper.ArticleLikeMapper;
import org.springframework.stereotype.Service;

/**
* @author 小新
* @description 针对表【article_like(文章点赞关系)】的数据库操作Service实现
* @createDate 2025-08-14 21:47:58
*/
@Service
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike>
    implements ArticleLikeService{

}




