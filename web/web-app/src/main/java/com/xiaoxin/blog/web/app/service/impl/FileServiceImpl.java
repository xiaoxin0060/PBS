package com.xiaoxin.blog.web.app.service.impl;

import com.xiaoxin.blog.common.minio.MinioProperties;
import com.xiaoxin.blog.web.app.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private MinioProperties properties;

    @Autowired
    private MinioClient client;

    @Override
    public String uploadImage(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        check();
        String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        client.putObject(PutObjectArgs.builder().object(filename).bucket(properties.getBucketName())
                                      .stream(file.getInputStream(), file.getSize(), -1)
                                      .contentType(file.getContentType()).build());
        return String.join("/", properties.getEndpoint(), properties.getBucketName(), filename);

    }

    private String createBucketPolicyConfig(String bucketName)
    {

        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }


    @Override
    public List<String> uploadImages(MultipartFile[] files) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        // 一次性确保桶存在与策略设置（避免对每个文件重复检查）
        check();

        String dateDir = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String endpoint = properties.getEndpoint();
        String bucket = properties.getBucketName();

        return Arrays.stream(files)
                     .filter(Objects::nonNull)
                     .filter(f -> !f.isEmpty())
                     .map(file -> {
                         String originalName = file.getOriginalFilename();
                         String filename = dateDir + "/" + UUID.randomUUID() + "-" + (originalName == null ? "unnamed" : originalName);
                         try {
                             client.putObject(PutObjectArgs.builder()
                                                           .bucket(bucket)
                                                           .object(filename)
                                                           .stream(file.getInputStream(), file.getSize(), -1)
                                                           .contentType(file.getContentType())
                                                           .build());
                         } catch (Exception e) {
                             // 在 lambda 中传播受检异常，交给全局异常处理器
                             throw new RuntimeException(e);
                         }
                         return String.join("/", endpoint, bucket, filename);
                     })
                     .collect(Collectors.toList());

    }

    private void check() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException
    {
        boolean bucketExists = client.bucketExists(
                BucketExistsArgs.builder().bucket(properties.getBucketName()).build()
        );
        if (!bucketExists) {
            client.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucketName()).build());
            client.setBucketPolicy(SetBucketPolicyArgs.builder()
                                                      .bucket(properties.getBucketName())
                                                      .config(createBucketPolicyConfig(properties.getBucketName()))
                                                      .build());
        }
    }
}
