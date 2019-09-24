package com.link.demopac.cloudstroredemo.cloud;

import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

public interface CloudStorageService {

    /**
     * 文件上传
     * @param key
     * @param file
     * @throws Exception
     */
    void uploadFile(String key, MultipartFile file) throws Exception;

    /**
     * 文件下载
     * @param key
     * @param out
     * @throws Exception
     */
    void download(String key, OutputStream out) throws Exception;

    /**
     * 文件删除
     * @param key
     * @throws Exception
     */
    void delete(String key) throws Exception;

    /**
     * 生成访问链接
     * @param key
     * @return
     * @throws Exception
     */
    String generateDownloadURL(String key) throws Exception;
}
