package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.model.entity.Comment;
import com.xiaoxin.blog.web.app.dto.AddCommentDto;
import com.xiaoxin.blog.web.app.dto.CommentQueryDto;
import com.xiaoxin.blog.web.app.mapper.CommentMapper;
import com.xiaoxin.blog.web.app.service.CommentService;
import com.xiaoxin.blog.web.app.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author 小新
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public IPage<CommentVo> getComments(CommentQueryDto queryDto)
    {
        IPage<CommentVo> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());

        return commentMapper.getComments(page,queryDto);
    }

    @Override
    public CommentVo addComment(AddCommentDto commentDto)
    {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto,comment);
        //要经过后台审核
        comment.setStatus(1L);
        commentMapper.insert( comment);
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        commentVo.setUserName(LoginUserHolder.get().getUsername());
        return commentVo;
    }

    @Override
    public void deleteUserComment(Long id)
    {
        Comment comment = commentMapper.selectById(id);
        if(comment == null) throw new BlogException(ResultCodeEnum.APP_DATA_NOT_EXIST);
        if(!Objects.equals(comment.getUserId(), LoginUserHolder.get().getUserId())) throw new BlogException(ResultCodeEnum.APP_NO_AUTH);
        commentMapper.deleteById(comment);
    }

    @Override
    public IPage<CommentVo> getMyComments(CommentQueryDto queryDto)
    {
        IPage<CommentVo> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        return commentMapper.getComments(page,queryDto);
    }
}




