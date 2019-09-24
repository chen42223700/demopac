package com.link.demopac.cloudstroredemo.cloud;

import com.link.demopac.Utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CloudFactory {
	
	@Resource(name = "azureCloudImpl")
	private CloudStorageService azure;
	
	@Resource(name = "aliyunCloudImpl")
	private CloudStorageService aliyun;
	
    @Value("${cloud.switch}")
    private String cloudSwitch;

	/**
	 * 获取上传服务
	 * @return
	 */
	public CloudStorageService getCloud() {
		CloudStorageService cloud = null;
    	if (Constants.AZURE.equals(cloudSwitch)) {
			cloud = azure;
		}else {
			cloud = aliyun;
		}
    	return cloud;
    }
}
