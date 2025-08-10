package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Comment;
import com.xiaoxin.blog.web.admin.mapper.CommentMapper;
import com.xiaoxin.blog.web.admin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void restoreComment(Long id) {
        commentMapper.restoreComment(id);
    }
}




