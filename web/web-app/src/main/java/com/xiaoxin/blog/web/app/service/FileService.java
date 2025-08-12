package com.xiaoxin.blog.web.app.service;

import com.xiaoxin.blog.web.app.vo.FileUploadVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService{
    FileUploadVo uploadImage(MultipartFile file, String type);

    List<FileUploadVo> uploadImages(MultipartFile[] files, String type);
}
