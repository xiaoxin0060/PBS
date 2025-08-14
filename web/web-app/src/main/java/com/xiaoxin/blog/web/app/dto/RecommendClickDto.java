package com.xiaoxin.blog.web.app.dto;

import com.xiaoxin.blog.model.enums.TargetType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendClickDto {
    // 0=ARTICLE,1=USER,2=CATEGORY,3=TAG
    @NotNull @Min(0) @Max(3)
    private TargetType targetType;

    @NotNull @Min(1)
    private Long targetId;
}
