package com.xiaoxin.blog.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.blog.model.entity.Tag;
import com.xiaoxin.blog.web.admin.vo.PopularTagsVo;

import java.util.List;

/**
* @author 小新
* @description 针对表【tag(标签表)】的数据库操作Mapper
* @createDate 2025-08-04 00:14:52
* @Entity com.xiaoxin.blog.model.entity.Tag
*/

public interface TagMapper extends BaseMapper<Tag> {

    List<PopularTagsVo> getPopularTags(Integer limit);

    void restoreTag(Long id);
}




