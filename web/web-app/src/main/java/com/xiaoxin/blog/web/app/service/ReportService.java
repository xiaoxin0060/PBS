package com.xiaoxin.blog.web.app.service;

import com.xiaoxin.blog.web.app.dto.FeedbackDto;
import com.xiaoxin.blog.web.app.dto.ReportArticleDto;
import com.xiaoxin.blog.web.app.dto.ReportCommentDto;
import com.xiaoxin.blog.web.app.dto.ReportUserDto;
import com.xiaoxin.blog.web.app.vo.ReportTypeVo;

import java.util.List;

public interface ReportService{
    Long reportArticle(ReportArticleDto reportDto);

    Long reportComment(ReportCommentDto reportDto);

    Long reportUser(ReportUserDto reportDto);

    Long submitFeedback(FeedbackDto feedbackDto);

    List<ReportTypeVo> getReportTypes(String category);
}
