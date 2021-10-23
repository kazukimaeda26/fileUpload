package com.example.fileUpload.controller;


import com.example.fileUpload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload/local")
    public void uploadLocal(@RequestParam("file")MultipartFile multipartFile) {
        fileUploadService.uploadToLocal(multipartFile);
    }

    @PostMapping("/upload/db")
    public void upLoadDb(@RequestParam("file")MultipartFile multipartFile) {

    }
}
