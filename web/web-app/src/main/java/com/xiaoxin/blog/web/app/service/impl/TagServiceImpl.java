package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Tag;
import com.xiaoxin.blog.web.app.mapper.TagMapper;
import com.xiaoxin.blog.web.app.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author 小新
* @description 针对表【tag(标签表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




