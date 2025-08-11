package com.xiaoxin.blog.web.app.controller.upload;

import com.xiaoxin.blog.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件上传")
@RequestMapping("/app/upload")
@RestController
public class UploadController {
    
    @Autowired
    private FileService fileService;
    
    @Operation(summary = "上传图片")
    @PostMapping("/image")
    public Result<FileUploadVo> uploadImage(@RequestParam MultipartFile file,
                                           @RequestParam(defaultValue = "article") String type) {
        FileUploadVo result = fileService.uploadImage(file, type);
        return Result.ok(result);
    }
    
    @Operation(summary = "批量上传图片")
    @PostMapping("/images")
    public Result<List<FileUploadVo>> uploadImages(@RequestParam MultipartFile[] files,
                                                  @RequestParam(defaultValue = "article") String type) {
        List<FileUploadVo> results = fileService.uploadImages(files, type);
        return Result.ok(results);
    }
}