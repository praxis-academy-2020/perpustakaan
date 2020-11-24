package com.project.perpustakaan.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public void setUploadDir(String file){
        this.uploadDir=file;
    }

    public String getUploadDir(){
        return this.uploadDir;
    }
    
}
