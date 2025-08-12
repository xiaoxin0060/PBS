package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.AutoSaveDraftDto;
import com.xiaoxin.blog.web.app.dto.SaveDraftDto;
import com.xiaoxin.blog.web.app.dto.UpdateDraftDto;
import com.xiaoxin.blog.web.app.service.DraftService;
import com.xiaoxin.blog.web.app.vo.DraftDetailVo;
import com.xiaoxin.blog.web.app.vo.DraftVo;
import org.springframework.stereotype.Service;

@Service
public class DraftServiceImpl implements DraftService{
    @Override
    public Long saveDraft(SaveDraftDto draftDto)
    {
        return 0L;
    }

    @Override
    public IPage<DraftVo> getDrafts(Integer page, Integer size)
    {
        return null;
    }

    @Override
    public DraftDetailVo getDraftDetail(Long id)
    {
        return null;
    }

    @Override
    public void updateDraft(Long id, UpdateDraftDto draftDto)
    {

    }

    @Override
    public void deleteDraft(Long id)
    {

    }

    @Override
    public Long publishDraft(Long id)
    {
        return 0L;
    }

    @Override
    public Long autoSaveDraft(AutoSaveDraftDto draftDto)
    {
        return 0L;
    }
}
