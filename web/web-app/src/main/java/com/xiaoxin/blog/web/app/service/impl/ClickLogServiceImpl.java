package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.ClickLog;
import com.xiaoxin.blog.web.app.service.ClickLogService;
import com.xiaoxin.blog.web.app.mapper.ClickLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 小新
* @description 针对表【click_log】的数据库操作Service实现
* @createDate 2025-08-15 02:42:42
*/
@Service
public class ClickLogServiceImpl extends ServiceImpl<ClickLogMapper, ClickLog>
    implements ClickLogService{

}




