package com.xiaoxin.blog.common.exception;

import com.xiaoxin.blog.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class BlogException extends RuntimeException {
    private Integer code;

    public BlogException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    public BlogException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

}
