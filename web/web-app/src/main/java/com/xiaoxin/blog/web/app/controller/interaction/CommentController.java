package com.xiaoxin.blog.web.app.controller.interaction;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.AddCommentDto;
import com.xiaoxin.blog.web.app.dto.CommentQueryDto;
import com.xiaoxin.blog.web.app.dto.MyCommentQueryDto;
import com.xiaoxin.blog.web.app.service.CommentService;
import com.xiaoxin.blog.web.app.vo.CommentVo;
import com.xiaoxin.blog.web.app.vo.MyCommentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "评论互动")
@RequestMapping("/app/comments")
@RestController
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @Operation(summary = "获取文章评论")
    @GetMapping
    public Result<IPage<CommentVo>> getComments(CommentQueryDto queryDto) {
        IPage<CommentVo> comments = commentService.getComments(queryDto);
        return Result.ok(comments);
    }
    
    @Operation(summary = "发表评论")
    @PostMapping
    public Result<Long> addComment(@RequestBody @Valid AddCommentDto commentDto) {
        Long commentId = commentService.addComment(commentDto);
        return Result.ok(commentId);
    }
    
    @Operation(summary = "删除评论")
    @DeleteMapping("/{id}")
    public Result<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteUserComment(id);
        return Result.ok();
    }
    
    @Operation(summary = "获取我的评论")
    @GetMapping("/my")
    public Result<IPage<MyCommentVo>> getMyComments(MyCommentQueryDto queryDto) {
        IPage<MyCommentVo> comments = commentService.getMyComments(queryDto);
        return Result.ok(comments);
    }
}