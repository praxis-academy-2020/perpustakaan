package com.project.perpustakaan.payload;

public class UploadFileResponse {
    String fileName;
    String fileDownloadUri, filetype;
    long size;
    
    public UploadFileResponse(String fileName, String fileDownloadUri, String filetype, long size){
        this.fileName=fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.filetype = filetype;
        this.size = size;
    }

    void setFileName(String fileName){
        this.fileName = fileName;
    }

    String getFileName(){
        return this.fileName;
    }

    void setFileDownloadUri(String fileDownloadUri){
        this.fileDownloadUri = fileDownloadUri;
    }

    String getFileDowloadUri(){
        return this.fileDownloadUri;
    }

    void setFiletype(String filetype){
        this.filetype = filetype;
    }

    String getFiletype(){
        return this.filetype;
    }

    void setSize(long size){
        this.size = size;
    }    
    
    long getSize(){
        return this.size;
    }

}
