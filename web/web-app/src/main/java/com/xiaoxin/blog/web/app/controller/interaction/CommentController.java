package com.xiaoxin.blog.web.app.controller.interaction;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.dto.AddCommentDto;
import com.xiaoxin.blog.web.app.dto.CommentQueryDto;
import com.xiaoxin.blog.web.app.service.CommentService;
import com.xiaoxin.blog.web.app.vo.CommentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Tag(name = "评论互动")
@RequestMapping("/app/comments")
@RestController
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @Operation(summary = "获取文章评论")
    @GetMapping
    public Result<IPage<CommentVo>> getComments(@Parameter(description = "页码")@RequestParam Integer pageNum,
                                                @Parameter(description = "每页大小")@RequestParam Integer pageSize,
                                                @Parameter(description = "文章Id") @RequestParam(required = false) Long articleId,
                                                @Parameter(description = "用户Id") @RequestParam(required = false) Long userId,
                                                @Parameter(description = "父评论Id") @RequestParam(required = false) Long parentId) {
        CommentQueryDto queryDto = CommentQueryDto.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .articleId(articleId)
                .userId(userId)
                .parentId(parentId)
                .build();
        IPage<CommentVo> comments = commentService.getComments(queryDto);
        return Result.ok(comments);
    }
    
    @Operation(summary = "发表评论")
    @PostMapping
    public Result<CommentVo> addComment(@Parameter(description = "文章Id")@RequestParam Long articleId,
                                        @Parameter(description = "父评论Id")@RequestParam Long parentId,
                                        @Parameter(description = "评论内容")@RequestParam String content) {
        AddCommentDto commentDto = AddCommentDto.builder().userId(LoginUserHolder.get().getUserId()).createTime(new Date())
                                           .articleId(articleId).parentId(parentId).content(content)
                                           .build();
        CommentVo commentVo = commentService.addComment(commentDto);
        return Result.ok(commentVo);
    }
    
    @Operation(summary = "删除评论")
    @DeleteMapping("/{id}")
    public Result<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteUserComment(id);
        return Result.ok();
    }
    
    @Operation(summary = "获取我的评论")
    @GetMapping("/my")
    public Result<IPage<CommentVo>> getMyComments(@Parameter(description = "页码")@RequestParam Integer pageNum,
                                                    @Parameter(description = "每页大小")@RequestParam Integer pageSize,
                                                    @Parameter(description = "文章Id")@RequestParam(required = false) Long articleId,
                                                    @Parameter(description = "父评论Id")@RequestParam(required = false) Long parentId) {
        CommentQueryDto queryDto = CommentQueryDto.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .articleId(articleId)
                .parentId(parentId)
                .userId(LoginUserHolder.get().getUserId())
                .build();
        IPage<CommentVo> comments = commentService.getMyComments(queryDto);
        return Result.ok(comments);
    }
}