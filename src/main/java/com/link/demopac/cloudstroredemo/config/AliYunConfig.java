package com.link.demopac.cloudstroredemo.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliYunConfig {
	
	@Value("${spring.aliyun.endPoint}")
    private String  endPoint;
	
	@Value("${spring.aliyun.accessKeyID}")
    private String  accessKeyID;
	
	@Value("${spring.aliyun.accessKeySecret}")
    private String  accessKeySecret;

    @Value("${spring.aliyun.bucket}")
    private String bucketName;

    @Value("${cloud.switch}")
    private String cloud;

    @Bean
    public OSSClient getCOSClient() {
        OSSClient ossClient = new  OSSClient(endPoint, accessKeyID, accessKeySecret);
        return ossClient;
    }

}
