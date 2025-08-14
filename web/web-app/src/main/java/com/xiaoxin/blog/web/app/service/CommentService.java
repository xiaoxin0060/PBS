package com.xiaoxin.blog.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.Comment;
import com.xiaoxin.blog.web.app.dto.AddCommentDto;
import com.xiaoxin.blog.web.app.dto.CommentQueryDto;
import com.xiaoxin.blog.web.app.vo.CommentVo;

/**
* @author 小新
* @description 针对表【comment(评论表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface CommentService extends IService<Comment> {

    IPage<CommentVo> getComments(CommentQueryDto queryDto);

    CommentVo addComment(AddCommentDto commentDto);

    void deleteUserComment(Long id);

    IPage<CommentVo> getMyComments(CommentQueryDto queryDto);
}
