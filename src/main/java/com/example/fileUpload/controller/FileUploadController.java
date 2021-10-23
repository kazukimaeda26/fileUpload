package com.example.fileUpload.controller;


import com.example.fileUpload.model.UploadedFile;
import com.example.fileUpload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        fileUploadService.uploadToDb(multipartFile);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
        UploadedFile uploadedFile = fileUploadService.downloadFile(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadedFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= "+uploadedFile.getFileName())
                .body(new ByteArrayResource(uploadedFile.getFileData()));
    }
}
