package com.link.demopac.cloudstroredemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudService {

    /**
     * 上传图片到oss存储
     * @throws Exception
     */
    public void uploadFileToCloud(MultipartFile file) throws Exception;

    /**
     * 查询文件访问url
     * @param key
     * @return
     * @throws Exception
     */
    public String queryFile(String key) throws Exception;
}
