package com.xiaoxin.blog.web.app.mapper;

import com.xiaoxin.blog.model.entity.ClickLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.blog.web.app.dto.RecommendClickDto;

/**
* @author 小新
* @description 针对表【click_log】的数据库操作Mapper
* @createDate 2025-08-15 02:42:42
* @Entity com.xiaoxin.blog.model.entity.ClickLog
*/
public interface ClickLogMapper extends BaseMapper<ClickLog> {

    void recordRecommendClick(Long userId, RecommendClickDto clickDto);
}




