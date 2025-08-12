package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.CreateFavoriteFolderDto;
import com.xiaoxin.blog.web.app.service.FavoriteService;
import com.xiaoxin.blog.web.app.vo.FavoriteArticleVo;
import com.xiaoxin.blog.web.app.vo.FavoriteFolderVo;
import com.xiaoxin.blog.web.app.vo.FavoriteStatusVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FavoriteServiceImpl implements FavoriteService{
    @Override
    public FavoriteStatusVo toggleArticleFavorite(Long articleId)
    {
        return null;
    }

    @Override
    public List<FavoriteFolderVo> getFavoriteFolders()
    {
        return List.of();
    }

    @Override
    public Long createFavoriteFolder(CreateFavoriteFolderDto folderDto)
    {
        return 0L;
    }

    @Override
    public IPage<FavoriteArticleVo> getFavoriteArticles(Long folderId, Integer page, Integer size)
    {
        return null;
    }

    @Override
    public Map<Long,Boolean> checkFavoriteStatus(List<Long> articleIds)
    {
        return Map.of();
    }
}
