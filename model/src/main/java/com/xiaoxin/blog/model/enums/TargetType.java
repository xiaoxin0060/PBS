package com.xiaoxin.blog.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TargetType{
    ARTICLE(0),
    USER(1),
    CATEGORY(2),
    TAG(3);

    @EnumValue
    private final Integer code;
}
