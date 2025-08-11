package com.xiaoxin.blog.web.admin.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.model.entity.Tag;
import com.xiaoxin.blog.web.admin.service.TagService;
import com.xiaoxin.blog.web.admin.vo.PopularTagsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/tags")
@io.swagger.v3.oas.annotations.tags.Tag(name = "标签信息管理", description = "标签的增删改查接口")
public class TagController{
    @Autowired
    private TagService tagService;

    @Operation(summary = "获取所有标签")
    @GetMapping
    public Result<List<Tag>> getAllTags()
    {
        // 获取所有标签的实现
        List<Tag> list = tagService.list();
        return Result.ok(list);
    }

    @Operation(summary = "根据ID获取标签")
    @GetMapping("/{id}")
    public Result<Tag> getTagById(@Parameter(description = "标签ID") @PathVariable Long id)
    {
        // 根据ID获取标签的实现
        Tag tag = tagService.getById(id);
        return Result.ok(tag);
    }

    @Operation(summary = "创建新标签")
    @PostMapping
    public Result createTag(@Parameter(description = "标签信息") @RequestBody @Valid Tag tag)
    {
        // 创建标签的实现
        tagService.save(tag);
        return Result.ok();
    }

    @Operation(summary = "更新标签")
    @PutMapping("/{id}")
    public Result<Tag> updateTag(
            @Parameter(description = "标签ID") @PathVariable Long id,
            @Parameter(description = "标签信息") @RequestBody @Valid Tag tag)
    {
        // 更新标签的实现
        tag.setId(id);
        tagService.updateById(tag);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除标签")
    @DeleteMapping("/{id}")
    public Result deleteTag(@Parameter(description = "标签ID") @PathVariable Long id)
    {
        // 逻辑删除标签的实现
        LambdaUpdateWrapper<Tag> updateWrapper = new LambdaUpdateWrapper<Tag>()
                .eq(Tag::getId, id)
                .set(Tag::getDeleted, 1);
        tagService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "恢复已删除的标签")
    @PutMapping("/{id}/restore")
    public Result restoreTag(@Parameter(description = "标签ID") @PathVariable Long id)
    {
        // 恢复已删除标签的实现
        tagService.restoreTag(id);
        return Result.ok();
    }

    @Operation(summary = "批量获取标签")
    @PostMapping("/batch")
    public Result<List<Tag>> batchGetTags(@Parameter(description = "标签ID列表") @RequestBody List<Long> ids)
    {
        if(ids == null || ids.isEmpty()){
            throw new BlogException(ResultCodeEnum.PARAM_ERROR);
        }
        List<Tag> tags = tagService.listByIds(ids);
        return Result.ok(tags);
    }

    @Operation(summary = "获取热门标签")
    @GetMapping("/popular")
    public Result<List<PopularTagsVo>> getPopularTags(
            @Parameter(description = "获取数量") @RequestParam(defaultValue = "10") Integer limit)
    {
        // 获取热门标签的实现
        List<PopularTagsVo> tags = tagService.getPopularTags(limit);
        return Result.ok(tags);
    }

    @Operation(summary = "手动更新热门标签缓存")
    @PostMapping("/popular/refresh")
    public Result refreshPopularTagsCache()
    {
        tagService.updatePopularTagsCache();
        return Result.ok();
    }
}