package com.link.demopac.cloudstroredemo.service.impl;

import com.link.demopac.cloudstroredemo.cloud.CloudFactory;
import com.link.demopac.cloudstroredemo.cloud.CloudStorageService;
import com.link.demopac.cloudstroredemo.service.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class CloudServiceImpl implements CloudService {

    @Autowired
    private CloudFactory cloudFactory;

    /**
     * 上传图片到oss存储
     * @throws Exception
     */
    public void uploadFileToCloud(MultipartFile file) throws Exception{
        CloudStorageService cloudStorageService = cloudFactory.getCloud();
        cloudStorageService.uploadFile(UUID.randomUUID().toString(), file);
    };
}
