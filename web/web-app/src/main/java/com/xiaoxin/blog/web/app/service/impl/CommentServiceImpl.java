package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Comment;
import com.xiaoxin.blog.web.app.dto.AddCommentDto;
import com.xiaoxin.blog.web.app.dto.CommentQueryDto;
import com.xiaoxin.blog.web.app.dto.MyCommentQueryDto;
import com.xiaoxin.blog.web.app.mapper.CommentMapper;
import com.xiaoxin.blog.web.app.service.CommentService;
import com.xiaoxin.blog.web.app.vo.CommentVo;
import com.xiaoxin.blog.web.app.vo.MyCommentVo;
import org.springframework.stereotype.Service;

/**
* @author 小新
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

    @Override
    public IPage<CommentVo> getComments(CommentQueryDto queryDto)
    {
        return null;
    }

    @Override
    public Long addComment(AddCommentDto commentDto)
    {
        return 0L;
    }

    @Override
    public void deleteUserComment(Long id)
    {

    }

    @Override
    public IPage<MyCommentVo> getMyComments(MyCommentQueryDto queryDto)
    {
        return null;
    }
}




