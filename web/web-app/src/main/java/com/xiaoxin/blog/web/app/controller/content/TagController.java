package com.xiaoxin.blog.web.app.controller.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.service.TagService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.TagCloudVo;
import com.xiaoxin.blog.web.app.vo.TagDetailVo;
import com.xiaoxin.blog.web.app.vo.TagVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(name = "文章标签")
@RequestMapping("/app/tags")
@RestController
public class TagController {
    
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleService articleService;
    
    @Operation(summary = "获取所有标签")
    @GetMapping
    public Result<List<TagVo>> getAllTags(
            @RequestParam(defaultValue = "false") Boolean hot,
            @RequestParam(defaultValue = "50") Integer limit) {
        List<TagVo> tags = tagService.getTags(hot, limit);
        return Result.ok(tags);
    }
    
    @Operation(summary = "获取热门标签")
    @GetMapping("/hot")
    public Result<List<TagVo>> getHotTags(
            @RequestParam(defaultValue = "20") Integer limit) {
        List<TagVo> tags = tagService.getHotTags(limit);
        return Result.ok(tags);
    }
    
    @Operation(summary = "获取标签详情")
    @GetMapping("/{id}")
    public Result<TagDetailVo> getTagDetail(@PathVariable Long id) {
        TagDetailVo tag = tagService.getTagDetail(id);
        return Result.ok(tag);
    }
    
    @Operation(summary = "获取标签下的文章")
    @GetMapping("/{id}/articles")
    public Result<IPage<ArticleListVo>> getTagArticles(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "createTime") String sort) {
        
        ArticleQueryDto queryDto = ArticleQueryDto.builder()
                                                  .tagIds(Collections.singletonList(id))
                                                  .page(page)
                                                  .size(size)
                                                  .sort(sort)
                                                  .build();

        IPage<ArticleListVo> articles = articleService.getArticleList(queryDto);
        return Result.ok(articles);
    }
    
    @Operation(summary = "搜索标签")
    @GetMapping("/search")
    public Result<List<TagVo>> searchTags(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "10") Integer limit) {
        List<TagVo> tags = tagService.searchTags(keyword, limit);
        return Result.ok(tags);
    }
    
    @Operation(summary = "获取标签云")
    @GetMapping("/cloud")
    public Result<List<TagCloudVo>> getTagCloud(
            @RequestParam(defaultValue = "100") Integer limit) {
        List<TagCloudVo> tagCloud = tagService.getTagCloud(limit);
        return Result.ok(tagCloud);
    }
}