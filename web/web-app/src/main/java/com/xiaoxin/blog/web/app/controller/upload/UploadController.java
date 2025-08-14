package com.xiaoxin.blog.web.app.controller.upload;

import com.xiaoxin.blog.common.result.Result;
import com.xiaoxin.blog.web.app.service.FileService;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Tag(name = "文件上传")
@RequestMapping("/app/upload")
@RestController
public class UploadController {
    
    @Autowired
    private FileService fileService;
    
    @Operation(summary = "上传图片")
    @PostMapping("/image")
    public Result<String> uploadImage(@RequestParam MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        String url = fileService.uploadImage(file);
        return Result.ok(url);
    }
    
    @Operation(summary = "批量上传图片")
    @PostMapping("/images")
    public Result<List<String>> uploadImages(@RequestParam MultipartFile[] files) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        List<String> urls = fileService.uploadImages(files);
        return Result.ok(urls);
    }
}