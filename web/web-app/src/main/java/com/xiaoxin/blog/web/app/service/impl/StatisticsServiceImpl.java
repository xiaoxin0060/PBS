package com.xiaoxin.blog.web.app.service.impl;

import com.xiaoxin.blog.web.app.service.StatisticsService;
import com.xiaoxin.blog.web.app.vo.MyStatisticsVo;
import com.xiaoxin.blog.web.app.vo.PopularStatisticsVo;
import com.xiaoxin.blog.web.app.vo.RealtimeStatisticsVo;
import com.xiaoxin.blog.web.app.vo.SiteOverviewVo;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService{
    @Override
    public SiteOverviewVo getSiteOverview()
    {
        return null;
    }

    @Override
    public MyStatisticsVo getMyStatistics()
    {
        return null;
    }

    @Override
    public PopularStatisticsVo getPopularStatistics(Integer days)
    {
        return null;
    }

    @Override
    public RealtimeStatisticsVo getRealtimeStatistics()
    {
        return null;
    }
}
