package com.example.fileUpload.service.serviceImpl;

import com.example.fileUpload.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private String upLoadFolderPath = "/Users/maedakazuki/desktop/uploaded_";
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
}
