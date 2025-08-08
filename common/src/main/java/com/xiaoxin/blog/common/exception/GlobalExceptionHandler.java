package com.xiaoxin.blog.common.exception;

import com.xiaoxin.blog.common.result.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) // 捕获所有Exception及子类
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(BlogException.class) //
    public Result handleBlogException(BlogException e) {
        e.printStackTrace(); // 打印异常
        return Result.fail(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        // 提取第一个不合法的字段
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(null,msg); // 根据返回值封装
    }
}
