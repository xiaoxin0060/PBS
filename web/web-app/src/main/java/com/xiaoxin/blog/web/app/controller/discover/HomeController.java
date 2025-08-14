package com.xiaoxin.blog.web.app.controller.discover;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.service.HomeService;
import com.xiaoxin.blog.web.app.vo.HomeDataVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "首页发现")
@RequestMapping("/app/discover")
@RestController
public class HomeController {
    
    @Autowired
    private HomeService homeService;
    
    @Operation(summary = "获取首页数据")
    @GetMapping("/home")
    public Result<HomeDataVo> getHomeData() {
        HomeDataVo homeData = homeService.getHomeData();
        return Result.ok(homeData);
    }
}