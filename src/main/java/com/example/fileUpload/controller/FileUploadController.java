package com.example.fileUpload.controller;


import com.example.fileUpload.model.UploadedFile;
import com.example.fileUpload.response.FileUploadResponse;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


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
    public FileUploadResponse upLoadDb(@RequestParam("file")MultipartFile multipartFile) {
        UploadedFile uploadedFile = fileUploadService.uploadToDb(multipartFile);
        FileUploadResponse response = new FileUploadResponse();
        if (uploadedFile != null) {
            String downloaUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/download/")
                    .path(uploadedFile.getFileId())
                    .toUriString();
            response.setDownloadUri(downloaUri);
            response.setFileId(uploadedFile.getFileId());
            response.setFileType(uploadedFile.getFileType());
            response.setUploadStatus(true);
            response.setMessage("File Uploaded Successfully");
            return response;
        }
        response.setMessage("Oops 1 something went wrong please re-upload");
        return response;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
        UploadedFile uploadedFile = fileUploadService.downloadFile(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadedFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= "+uploadedFile.getFileName())
                .body(new ByteArrayResource(uploadedFile.getFileData()));
    }
}
