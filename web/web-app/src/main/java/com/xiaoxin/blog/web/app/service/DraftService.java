package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.web.app.dto.AutoSaveDraftDto;
import com.xiaoxin.blog.web.app.dto.SaveDraftDto;
import com.xiaoxin.blog.web.app.dto.UpdateDraftDto;
import com.xiaoxin.blog.web.app.vo.DraftDetailVo;
import com.xiaoxin.blog.web.app.vo.DraftVo;

public interface DraftService{
    Long saveDraft(SaveDraftDto draftDto);

    IPage<DraftVo> getDrafts(Integer page, Integer size);

    DraftDetailVo getDraftDetail(Long id);

    void updateDraft(Long id, UpdateDraftDto draftDto);

    void deleteDraft(Long id);

    void publishDraft(Long id);

    Long autoSaveDraft(AutoSaveDraftDto draftDto);
}
