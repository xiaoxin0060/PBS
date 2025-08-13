package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.login.LoginUser;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.model.entity.Article;
import com.xiaoxin.blog.web.app.dto.AutoSaveDraftDto;
import com.xiaoxin.blog.web.app.dto.SaveDraftDto;
import com.xiaoxin.blog.web.app.dto.UpdateDraftDto;
import com.xiaoxin.blog.web.app.mapper.ArticleMapper;
import com.xiaoxin.blog.web.app.service.DraftService;
import com.xiaoxin.blog.web.app.vo.DraftDetailVo;
import com.xiaoxin.blog.web.app.vo.DraftVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class DraftServiceImpl implements DraftService{
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Long saveDraft(SaveDraftDto draftDto)
    {
        LoginUser loginUser = LoginUserHolder.get();
        Article article = new Article();
        BeanUtils.copyProperties(draftDto, article);
        //设置为草稿
        article.setUserId(loginUser.getUserId());
        article.setStatus(0);
        article.setIsTop(0);
        article.setViewCount(0);
        article.setLikeCount(0);
        article.setCreateBy(loginUser.getUserId());
        articleMapper.insert(article);
        //返回ID,自动回显
        return article.getId();
    }

    @Override
    public IPage<DraftVo> getDrafts(Integer cur, Integer size)
    {
        Long userId = LoginUserHolder.get().getUserId();
        IPage<DraftVo> page = new Page<>(cur, size);
        return articleMapper.getDrafts(page, userId);
    }

    @Override
    public DraftDetailVo getDraftDetail(Long id)
    {
        Long userId = LoginUserHolder.get().getUserId();
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<Article>().eq(Article::getId, id)
                                                                               .eq(Article::getCreateBy, userId).eq(
                        Article::getStatus,
                        0);
        Article article = articleMapper.selectOne(wrapper);
        if(article == null) throw new BlogException(ResultCodeEnum.APP_DATA_NOT_EXIST);
        return new DraftDetailVo(article.getId(),
                LoginUserHolder.get().getUsername(),
                article.getTitle(),
                article.getContent(),
                article.getCategoryId());
    }

    @Override
    public void updateDraft(Long id, UpdateDraftDto draftDto)
    {
        Long userId = LoginUserHolder.get().getUserId();
        Article db = articleMapper.selectById(id);
        if (db == null || db.getStatus() != 0) {
            throw new BlogException(ResultCodeEnum.APP_DATA_NOT_EXIST);
        }
        if (!Objects.equals(db.getCreateBy(), userId)) {
            throw new BlogException(ResultCodeEnum.APP_NO_AUTH);
        }
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<Article>().eq(Article::getId, id)
                                                                               .eq(Article::getStatus, 0)
                                                                               .eq(Article::getCreateBy, userId);
        Article article = articleMapper.selectOne(wrapper);
        BeanUtils.copyProperties(draftDto, article);
        articleMapper.update(article, wrapper);
    }

    @Override
    public void deleteDraft(Long id)
    {
        articleMapper.deleteById(id);
    }

    @Override
    public void publishDraft(Long id)
    {
        Long userId = LoginUserHolder.get().getUserId();
        LambdaUpdateWrapper<Article> wrapper = new LambdaUpdateWrapper<Article>().eq(Article::getId, id)
                                                                                 .eq(Article::getStatus, 0)
                                                                                 .set(Article::getStatus, 1)
                                                                                 .eq(Article::getUserId, userId);
        articleMapper.update(wrapper);
    }

    @Override
    public Long autoSaveDraft(AutoSaveDraftDto draftDto)
    {
        LoginUser loginUser = LoginUserHolder.get();
        Long uid = loginUser.getUserId();

        if(draftDto.getId() == null){
            // 首次自动保存 -> 创建草稿
            Article article = new Article();
            article.setTitle(draftDto.getTitle());
            article.setContent(draftDto.getContent());
            article.setCoverImage(draftDto.getCoverImage());
            article.setCategoryId(draftDto.getCategoryId());

            article.setStatus(0); // 草稿
            article.setIsTop(0);
            article.setViewCount(0);
            article.setLikeCount(0);
            article.setCreateBy(uid);
            article.setCreateTime(new Date());
            article.setUpdateBy(uid);
            article.setUpdateTime(new Date());

            articleMapper.insert(article);
            return article.getId();
        }else{
            // 更新已有草稿：校验归属与状态
            Article db = articleMapper.selectById(draftDto.getId());
            if(db == null || db.getStatus() != 0){
                throw new BlogException(ResultCodeEnum.APP_DATA_NOT_EXIST);
            }
            if(!Objects.equals(db.getCreateBy(), uid)){
                throw new BlogException(ResultCodeEnum.APP_NO_AUTH);
            }

            //做基于 updateTime 的乐观锁，防止多端覆盖
            if(draftDto.getLastUpdateTime() != null && !Objects.equals(db.getUpdateTime(),
                    draftDto.getLastUpdateTime())){
                throw new BlogException(ResultCodeEnum.APP_DATA_CONFLICT);
            }

            Article patch = new Article();
            patch.setId(db.getId());
            patch.setTitle(draftDto.getTitle());
            patch.setContent(draftDto.getContent());
            patch.setCoverImage(draftDto.getCoverImage());
            patch.setCategoryId(draftDto.getCategoryId());
            patch.setUpdateBy(uid);
            patch.setUpdateTime(new Date());

            articleMapper.updateById(patch);
            return patch.getId();
        }
    }
}
