package com.xiaoxin.blog.web.app.controller.system;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.service.StatisticsService;
import com.xiaoxin.blog.web.app.vo.MyStatisticsVo;
import com.xiaoxin.blog.web.app.vo.PopularStatisticsVo;
import com.xiaoxin.blog.web.app.vo.RealtimeStatisticsVo;
import com.xiaoxin.blog.web.app.vo.SiteOverviewVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "统计信息")
@RequestMapping("/app/statistics")
@RestController
public class StatisticsController {
    
    @Autowired
    private StatisticsService statisticsService;
    
    @Operation(summary = "获取网站统计概览")
    @GetMapping("/overview")
    public Result<SiteOverviewVo> getSiteOverview() {
        SiteOverviewVo overview = statisticsService.getSiteOverview();
        return Result.ok(overview);
    }
    
    @Operation(summary = "获取我的统计信息")
    @GetMapping("/my")
    public Result<MyStatisticsVo> getMyStatistics() {
        MyStatisticsVo statistics = statisticsService.getMyStatistics();
        return Result.ok(statistics);
    }
    
    @Operation(summary = "获取热门统计")
    @GetMapping("/popular")
    public Result<PopularStatisticsVo> getPopularStatistics(
            @RequestParam(defaultValue = "7") Integer days) {
        PopularStatisticsVo stats = statisticsService.getPopularStatistics(days);
        return Result.ok(stats);
    }
    
    @Operation(summary = "获取实时统计")
    @GetMapping("/realtime")
    public Result<RealtimeStatisticsVo> getRealtimeStatistics() {
        RealtimeStatisticsVo stats = statisticsService.getRealtimeStatistics();
        return Result.ok(stats);
    }
}