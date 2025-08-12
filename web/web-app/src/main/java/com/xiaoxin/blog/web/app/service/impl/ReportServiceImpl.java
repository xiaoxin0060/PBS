package com.xiaoxin.blog.web.app.service.impl;

import com.xiaoxin.blog.web.app.dto.FeedbackDto;
import com.xiaoxin.blog.web.app.dto.ReportArticleDto;
import com.xiaoxin.blog.web.app.dto.ReportCommentDto;
import com.xiaoxin.blog.web.app.dto.ReportUserDto;
import com.xiaoxin.blog.web.app.service.ReportService;
import com.xiaoxin.blog.web.app.vo.ReportTypeVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    @Override
    public Long reportArticle(ReportArticleDto reportDto)
    {
        return 0L;
    }

    @Override
    public Long reportComment(ReportCommentDto reportDto)
    {
        return 0L;
    }

    @Override
    public Long reportUser(ReportUserDto reportDto)
    {
        return 0L;
    }

    @Override
    public Long submitFeedback(FeedbackDto feedbackDto)
    {
        return 0L;
    }

    @Override
    public List<ReportTypeVo> getReportTypes(String category)
    {
        return List.of();
    }
}
