package com.xiaoxin.blog.web.app.service.impl;

import com.xiaoxin.blog.web.app.service.FileService;
import com.xiaoxin.blog.web.app.vo.FileUploadVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public FileUploadVo uploadImage(MultipartFile file, String type)
    {
        return null;
    }

    @Override
    public List<FileUploadVo> uploadImages(MultipartFile[] files, String type)
    {
        return List.of();
    }
}
