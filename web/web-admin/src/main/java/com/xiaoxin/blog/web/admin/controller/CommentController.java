package com.xiaoxin.blog.web.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.model.entity.Comment;
import com.xiaoxin.blog.web.admin.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/comments")  // 复数形式
@Tag(name = "评论信息管理", description = "评论的增删改查、文章评论查询等接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Operation(summary = "获取所有评论")
    @GetMapping
    public Result<IPage<Comment>> getAllComments(
            @Parameter(description = "页码") @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页条数") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        // 获取所有评论，支持分页
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        IPage<Comment> list = commentService.page(page);
        return Result.ok(list);
    }

    @Operation(summary = "根据ID获取评论")
    @GetMapping("/{id}")  // 路径参数
    public Result<Comment> getCommentById(
            @Parameter(description = "评论ID") @PathVariable Long id) {
        // 根据ID获取评论
        Comment comment = commentService.getById(id);
        return Result.ok(comment);
    }

    @Operation(summary = "创建评论")
    @PostMapping
    public Result createComment(
            @Parameter(description = "评论信息") @RequestBody @Valid Comment comment) {
        // 创建新评论
        commentService.save(comment);
        return Result.ok();
    }

    @Operation(summary = "更新评论")
    @PutMapping("/{id}")
    public Result updateComment(
            @Parameter(description = "评论ID") @PathVariable Long id,
            @Parameter(description = "评论信息") @RequestBody @Valid Comment comment) {
        // 更新评论
        LambdaUpdateWrapper<Comment> updateWrapper = new LambdaUpdateWrapper<Comment>().eq(Comment::getId, id)
                .set(Comment::getContent, comment.getContent());
        commentService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除评论")
    @DeleteMapping("/{id}")
    public Result deleteComment(
            @Parameter(description = "评论ID") @PathVariable Long id) {
        // 逻辑删除评论
        LambdaUpdateWrapper<Comment> updateWrapper = new LambdaUpdateWrapper<Comment>().eq(Comment::getId, id)
                .set(Comment::getDeleted,1);
        commentService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "恢复已删除的评论")
    @PutMapping("/{id}/restore")
    public Result restoreComment(
            @Parameter(description = "评论ID") @PathVariable Long id) {
        // 恢复已删除评论
        commentService.restoreComment(id);
        return Result.ok();
    }

    @Operation(summary = "根据文章ID获取评论")
    @GetMapping("/articles/{articleId}")
    public Result<IPage<Comment>> getCommentsByArticleId(
            @Parameter(description = "文章ID") @PathVariable Long articleId,
            @Parameter(description = "页码") @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页条数") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        // 根据文章ID获取评论，支持分页
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>().eq(Comment::getArticleId, articleId);
        IPage<Comment> list = commentService.page(page, queryWrapper);
        return Result.ok(list);
    }

    @Operation(summary = "批量审核评论")
    @PatchMapping("/batch/status")
    public Result batchUpdateStatus(
            @Parameter(description = "评论ID列表") @RequestBody List<Long> ids,
            @Parameter(description = "审核状态: 0-待审核, 1-通过, 2-拒绝") @RequestParam Integer status) {
        // 批量更新评论状态
        LambdaUpdateWrapper<Comment> updateWrapper = new LambdaUpdateWrapper<Comment>().in(Comment::getId, ids)
                .set(Comment::getStatus, status);
        commentService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "获取待审核评论")
    @GetMapping("/pending")
    public Result<IPage<Comment>> getPendingComments(
            @Parameter(description = "页码") @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页条数") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        // 获取待审核评论
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>().eq(Comment::getStatus,0);
        IPage<Comment> list = commentService.page(page, queryWrapper);
        return Result.ok(list);
    }
}