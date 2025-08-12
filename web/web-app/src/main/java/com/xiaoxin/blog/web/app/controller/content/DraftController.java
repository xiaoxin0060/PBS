package com.xiaoxin.blog.web.app.controller.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.AutoSaveDraftDto;
import com.xiaoxin.blog.web.app.dto.SaveDraftDto;
import com.xiaoxin.blog.web.app.dto.UpdateDraftDto;
import com.xiaoxin.blog.web.app.service.DraftService;
import com.xiaoxin.blog.web.app.vo.DraftDetailVo;
import com.xiaoxin.blog.web.app.vo.DraftVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "草稿管理")
@RequestMapping("/app/drafts")
@RestController
public class DraftController {
    
    @Autowired
    private DraftService draftService;
    
    @Operation(summary = "保存草稿")
    @PostMapping
    public Result<Long> saveDraft(@RequestBody @Valid SaveDraftDto draftDto) {
        Long draftId = draftService.saveDraft(draftDto);
        return Result.ok(draftId);
    }
    
    @Operation(summary = "获取草稿列表")
    @GetMapping
    public Result<IPage<DraftVo>> getDrafts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        IPage<DraftVo> drafts = draftService.getDrafts(page, size);
        return Result.ok(drafts);
    }
    
    @Operation(summary = "获取草稿详情")
    @GetMapping("/{id}")
    public Result<DraftDetailVo> getDraftDetail(@PathVariable Long id) {
        DraftDetailVo draft = draftService.getDraftDetail(id);
        return Result.ok(draft);
    }
    
    @Operation(summary = "更新草稿")
    @PutMapping("/{id}")
    public Result<Void> updateDraft(@PathVariable Long id, 
                                   @RequestBody @Valid UpdateDraftDto draftDto) {
        draftService.updateDraft(id, draftDto);
        return Result.ok();
    }
    
    @Operation(summary = "删除草稿")
    @DeleteMapping("/{id}")
    public Result<Void> deleteDraft(@PathVariable Long id) {
        draftService.deleteDraft(id);
        return Result.ok();
    }
    
    @Operation(summary = "发布草稿")
    @PostMapping("/{id}/publish")
    public Result<Long> publishDraft(@PathVariable Long id) {
        Long articleId = draftService.publishDraft(id);
        return Result.ok(articleId);
    }
    
    @Operation(summary = "自动保存草稿")
    @PostMapping("/auto-save")
    public Result<Long> autoSaveDraft(@RequestBody @Valid AutoSaveDraftDto draftDto) {
        Long draftId = draftService.autoSaveDraft(draftDto);
        return Result.ok(draftId);
    }
}