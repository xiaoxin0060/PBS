package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.admin.mapper.UserMapper;
import com.xiaoxin.blog.web.admin.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author 小新
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




