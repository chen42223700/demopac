package com.link.demopac.cloudstroredemo.cloud.impl;

import com.link.demopac.cloudstroredemo.cloud.CloudStorageService;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

@Component("azureCloudImpl")
public class AzureCloudImpl implements CloudStorageService {
	protected static Logger logger = LoggerFactory.getLogger(AzureCloudImpl.class);

	@Autowired
	private CloudBlobContainer container;

	@Override
	public void uploadFile(String azureKey, MultipartFile file) throws Exception {
			//获取储存blob对象
			CloudBlockBlob blob = container.getBlockBlobReference(azureKey);
			//上传文件
			blob.upload(file.getInputStream(), file.getSize());
	}

	@Override
	public void download(String azureKey, OutputStream out) throws Exception {
		//获取储存blob对象
		CloudBlockBlob blob = container.getBlockBlobReference(azureKey);
		//文件下载
		blob.download(out);
	}

	@Override
	public void delete(String azureKey) throws Exception {
		container.getBlockBlobReference(azureKey).deleteIfExists();
		
	}

	@Override
	public String generateDownloadURL(String azureKey) throws Exception {
		String url = null;
		//获取储存blob对象
		CloudBlockBlob blob = container.getBlockBlobReference(azureKey);
		if (null != blob){
			//获取对象链接
			url = blob.getUri().toString();
		}
		return url;
	}

}
