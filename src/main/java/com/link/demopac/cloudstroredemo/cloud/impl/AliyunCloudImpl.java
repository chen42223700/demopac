package com.link.demopac.cloudstroredemo.cloud.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.link.demopac.Utils.FileUtils;
import com.link.demopac.cloudstroredemo.cloud.CloudStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.Date;

@Component("aliyunCloudImpl")
public class AliyunCloudImpl implements CloudStorageService {
	protected static Logger logger = LoggerFactory.getLogger(AliyunCloudImpl.class);

	@Autowired(required = false)
	private OSS oss;
	
    @Value("${spring.aliyun.bucket}")
	private String bucketName;

	@Override
	public void uploadFile(String aliyunFileKey, MultipartFile file) throws Exception {
		//上传文件
		oss.putObject(bucketName, aliyunFileKey, file.getInputStream());
	}

	@Override
	public void download(String aliyunFileKey, OutputStream out) throws Exception {
		//获取对象应用
		OSSObject ossObject = oss.getObject(bucketName, aliyunFileKey);
		FileUtils.readFileContent(ossObject.getObjectContent(), out);
	}

	@Override
	public void delete(String aliyunFileKey) {
		//根据key删除对象
		oss.deleteObject(bucketName, aliyunFileKey);
	}

	@Override
	public String generateDownloadURL(String aliyunFileKey) {
		Date expiration = new Date(new Date().getTime() + 3600 * 1000);
		//获取对象访问链接
		return oss.generatePresignedUrl(bucketName, aliyunFileKey, expiration).toString();
	}

}
