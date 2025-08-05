package com.xiaoxin.blog.web.admin.controller;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
@Tag(name = "评论信息管理", description = "评论的增删改查、文章评论查询等接口")
public class CommentController {

    @Operation(summary = "查询所有评论")
    @GetMapping("/selectAllComment")
    public Result selectAllComment() {
        return Result.ok();
    }

    @Operation(summary = "根据ID查询评论")
    @PostMapping("/selectCommentById")
    public Result selectCommentById(@Parameter(description = "评论ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "新增或修改评论")
    @PostMapping("/updateOrSaveComment")
    public Result updateOrSaveComment() {
        return Result.ok();
    }

    @Operation(summary = "逻辑删除评论")
    @GetMapping("/removeComment")
    public Result removeComment(@Parameter(description = "评论ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "恢复逻辑删除的评论")
    @PutMapping("/recoverComment")
    public Result recoverComment(@Parameter(description = "评论ID") long id) {
        return Result.ok();
    }

    @Operation(summary = "根据文章ID查询全部评论")
    @GetMapping("/selectCommentByArticleId")
    public Result selectCommentByArticleId(@Parameter(description = "文章ID") long articleId) {
        return Result.ok();
    }
}