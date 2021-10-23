package com.example.fileUpload.service.serviceImpl;

import com.example.fileUpload.model.UploadedFile;
import com.example.fileUpload.repository.FileUploadRepository;
import com.example.fileUpload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private String upLoadFolderPath = "/Users/maedakazuki/desktop/uploaded_";
    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Override
    public void uploadToLocal(MultipartFile file) {

        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(upLoadFolderPath + file.getOriginalFilename());
            Files.write(path, data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadToDb(MultipartFile file) {

        UploadedFile uploadedFile = new UploadedFile();
        try {
            uploadedFile.setFileData(file.getBytes());
            // おそらくここで、ファイルタイプのチェックが可能。
            uploadedFile.setFileType(file.getContentType());
            uploadedFile.setFileName(file.getOriginalFilename());
            fileUploadRepository.save(uploadedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UploadedFile downloadFile(String fileId) {
        UploadedFile uploadedFile = fileUploadRepository.getById(fileId);
        return uploadedFile;
    }
}
