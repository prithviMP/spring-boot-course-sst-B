package com.example.rideshare.controller;


import com.example.rideshare.dto.FileUploadResponseDTO;
import com.example.rideshare.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public FileUploadResponseDTO upload(@RequestParam("file") MultipartFile file) {

        return fileUploadService.store(file);
    }
}
