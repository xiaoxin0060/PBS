package com.xiaoxin.blog.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.blog.model.entity.Tag;
import com.xiaoxin.blog.web.admin.vo.PopularTagsVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【tag(标签表)】的数据库操作Service
* @createDate 2025-08-04 00:14:52
*/
public interface TagService extends IService<Tag> {

    List<PopularTagsVo> getPopularTags(Integer limit);

    void updatePopularTagsCache();

    void restoreTag(Long id);
}
