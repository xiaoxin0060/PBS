package com.xiaoxin.blog.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoxin.blog.model.entity.Comment;
import com.xiaoxin.blog.web.app.dto.CommentQueryDto;
import com.xiaoxin.blog.web.app.vo.CommentVo;

/**
* @author 小新
* @description 针对表【comment(评论表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<CommentVo> getComments(IPage<CommentVo> page, CommentQueryDto queryDto);

}




