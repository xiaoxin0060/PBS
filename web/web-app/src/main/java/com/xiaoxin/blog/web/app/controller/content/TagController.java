package com.xiaoxin.blog.web.app.controller.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.ArticleQueryDto;
import com.xiaoxin.blog.web.app.service.ArticleService;
import com.xiaoxin.blog.web.app.service.TagService;
import com.xiaoxin.blog.web.app.vo.ArticleListVo;
import com.xiaoxin.blog.web.app.vo.TagVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<TagVo>> getAllTags() {
        List<TagVo> tags = tagService.getAllTags();
        return Result.ok(tags);
    }
    
    @Operation(summary = "获取热门标签")
    @GetMapping("/hot")
    public Result<List<TagVo>> getHotTags(
            @RequestParam(defaultValue = "20") Integer limit) {
        List<TagVo> tags = tagService.getHotTags(limit);
        return Result.ok(tags);
    }

    @Operation(summary = "获取标签下的文章")
    @PostMapping("/articles")
    public Result<IPage<ArticleListVo>> getTagArticles(
            @Parameter(description = "标签列表ID", example = "1")@RequestParam List<Long> tagIds,
            @Parameter(description = "文章状态：0 草稿，1 已发布")@RequestParam Integer status,
            @Parameter(description = "页码")@RequestParam Integer pageNum,
            @Parameter(description = "每页大小")@RequestParam Integer pageSize
            ) {
        ArticleQueryDto queryDto = ArticleQueryDto.builder()
                .tagIds(tagIds)
                .status(status)
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
        return Result.ok(tagService.getTagArticles(queryDto));
    }

}