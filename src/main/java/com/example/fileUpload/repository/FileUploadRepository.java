package com.example.fileUpload.repository;

import com.example.fileUpload.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadedFile, String> {

}
