package com.xiaoxin.blog.web.app.controller.system;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "举报反馈")
@RequestMapping("/app/reports")
@RestController
public class ReportController {
    
    @Autowired
    private ReportService reportService;
    
    @Operation(summary = "举报文章")
    @PostMapping("/article")
    public Result<Long> reportArticle(@RequestBody @Valid ReportArticleDto reportDto) {
        Long reportId = reportService.reportArticle(reportDto);
        return Result.ok(reportId);
    }
    
    @Operation(summary = "举报评论")
    @PostMapping("/comment")
    public Result<Long> reportComment(@RequestBody @Valid ReportCommentDto reportDto) {
        Long reportId = reportService.reportComment(reportDto);
        return Result.ok(reportId);
    }
    
    @Operation(summary = "举报用户")
    @PostMapping("/user")
    public Result<Long> reportUser(@RequestBody @Valid ReportUserDto reportDto) {
        Long reportId = reportService.reportUser(reportDto);
        return Result.ok(reportId);
    }
    
    @Operation(summary = "提交反馈")
    @PostMapping("/feedback")
    public Result<Long> submitFeedback(@RequestBody @Valid FeedbackDto feedbackDto) {
        Long feedbackId = reportService.submitFeedback(feedbackDto);
        return Result.ok(feedbackId);
    }
    
    @Operation(summary = "获取举报类型")
    @GetMapping("/types")
    public Result<List<ReportTypeVo>> getReportTypes(@RequestParam String category) {
        List<ReportTypeVo> types = reportService.getReportTypes(category);
        return Result.ok(types);
    }
}