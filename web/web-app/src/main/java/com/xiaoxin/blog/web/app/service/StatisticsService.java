package com.xiaoxin.blog.web.app.service;

import com.xiaoxin.blog.web.app.vo.MyStatisticsVo;
import com.xiaoxin.blog.web.app.vo.PopularStatisticsVo;
import com.xiaoxin.blog.web.app.vo.RealtimeStatisticsVo;
import com.xiaoxin.blog.web.app.vo.SiteOverviewVo;

public interface StatisticsService{
    SiteOverviewVo getSiteOverview();

    MyStatisticsVo getMyStatistics();

    PopularStatisticsVo getPopularStatistics(Integer days);

    RealtimeStatisticsVo getRealtimeStatistics();
}
