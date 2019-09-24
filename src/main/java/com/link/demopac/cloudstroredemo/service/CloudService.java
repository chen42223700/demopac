package com.link.demopac.cloudstroredemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudService {

    /**
     * 上传图片到oss存储
     * @throws Exception
     */
    public void uploadFileToCloud(MultipartFile file) throws Exception;
}
