package com.link.demopac.cloudstroredemo.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.link.demopac.Utils.Constants;
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
    public OSS getOSS() {
        if (!Constants.ALIYUN.equals(cloud)) return null;
        return new OSSClientBuilder().build(endPoint, accessKeyID, accessKeySecret);
    }

}
