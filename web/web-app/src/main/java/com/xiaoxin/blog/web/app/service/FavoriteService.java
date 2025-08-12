package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.CreateFavoriteFolderDto;
import com.xiaoxin.blog.web.app.vo.FavoriteArticleVo;
import com.xiaoxin.blog.web.app.vo.FavoriteFolderVo;
import com.xiaoxin.blog.web.app.vo.FavoriteStatusVo;

import java.util.List;
import java.util.Map;

public interface FavoriteService{
    FavoriteStatusVo toggleArticleFavorite(Long articleId);

    List<FavoriteFolderVo> getFavoriteFolders();

    Long createFavoriteFolder(CreateFavoriteFolderDto folderDto);

    IPage<FavoriteArticleVo> getFavoriteArticles(Long folderId, Integer page, Integer size);

    Map<Long,Boolean> checkFavoriteStatus(List<Long> articleIds);
}
