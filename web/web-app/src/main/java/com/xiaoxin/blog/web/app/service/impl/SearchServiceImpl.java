package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.ArticleSearchDto;
import com.xiaoxin.blog.web.app.dto.GlobalSearchDto;
import com.xiaoxin.blog.web.app.service.SearchService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.GlobalSearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{
    @Override
    public GlobalSearchVo globalSearch(GlobalSearchDto searchDto)
    {
        return null;
    }

    @Override
    public IPage<ArticleListVo> searchArticles(ArticleSearchDto searchDto)
    {
        return null;
    }

    @Override
    public List<String> getSearchSuggestions(String keyword, Integer limit)
    {
        return List.of();
    }

    @Override
    public List<String> getHotKeywords(Integer limit)
    {
        return List.of();
    }
}
