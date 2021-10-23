package com.example.fileUpload.service;

import com.example.fileUpload.model.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public void uploadToLocal(MultipartFile file);
    public UploadedFile uploadToDb(MultipartFile file);
    public UploadedFile downloadFile(String fileId);
}
