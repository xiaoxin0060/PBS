package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.ArticleSearchDto;
import com.xiaoxin.blog.web.app.dto.GlobalSearchDto;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.GlobalSearchVo;

import java.util.List;

public interface SearchService{
    GlobalSearchVo globalSearch(GlobalSearchDto searchDto);

    IPage<ArticleListVo> searchArticles(ArticleSearchDto searchDto);

    List<String> getSearchSuggestions(String keyword, Integer limit);

    List<String> getHotKeywords(Integer limit);
}
