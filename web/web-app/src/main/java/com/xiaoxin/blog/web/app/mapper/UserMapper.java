package com.xiaoxin.blog.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.app.vo.UserStatisticsVo;

/**
* @author 小新
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    UserStatisticsVo getUserStatistics(Long userId);
}




