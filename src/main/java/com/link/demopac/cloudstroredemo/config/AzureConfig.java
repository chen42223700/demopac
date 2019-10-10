package com.link.demopac.cloudstroredemo.config;

import com.link.demopac.Utils.Constants;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {
    @Value("${azure.storegepath}")
    private String azureStorageConnectionString;

    @Value("${azure.container}")
    private String containerStr;

    @Value("${cloud.switch}")
    private String cloud;

    @Bean
    public CloudBlobContainer getAzureContainer() throws Exception{
        if (!Constants.AZURE.equals(cloud)) return null;
        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(azureStorageConnectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            CloudBlobContainer container = blobClient.getContainerReference(containerStr);
            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER,  new BlobRequestOptions(), new OperationContext());
            return container;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
