package com.example.rideshare.service;

import com.example.rideshare.dto.FileUploadResponseDTO;
import com.example.rideshare.util.FileNameUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    private static final String UPLOAD_DIR_NAME = "uploads";

    private final Path uploadDir;


    public FileUploadService() {

        // To get the project root directory
        String projectRoot = System.getProperty("user.dir");

        // Build absolute path --> projectRootDirectory/uploads
        this.uploadDir = Paths.get(projectRoot, UPLOAD_DIR_NAME);
    }

    /**
     * Stores an uploaded file to disk.
     *
     * Steps:
     * 1. Validate the file (not null, not empty, has filename)
     * 2. Generate a unique filename to prevent overwrites  ---> uploads/
     * 3. Create upload directory if it doesn't exist
     * 4. Save file to disk
     * 5. Return response with filename and location
     *
     * @param file The multipart file uploaded by the client
     * @return FileUploadResponse containing the saved filename and location
     * @throws RuntimeException if validation fails or file cannot be saved
     */

    public FileUploadResponseDTO store(MultipartFile file) {

        //1. Validate the file (not null, not empty, has filename)

        if( file == null ) {
            throw new RuntimeException("File is null");
        }

        if(file.isEmpty() ) {
            throw new RuntimeException("File is empty");
        }

       // 2. Generate a unique filename to prevent overwrites

        String originalFilename = file.getOriginalFilename();

        if( originalFilename == null || originalFilename.isEmpty() ) {
            throw new RuntimeException("Filename is empty or null");
        }

        String fileName = FileNameUtil.getFileName(originalFilename);

        //3. Create upload directory if it doesn't exist
        try{
            if(!Files.exists(uploadDir)){
                Files.createDirectories(uploadDir);
            }
        }catch (Exception e){
            throw new RuntimeException("Cannot create directory");
        }



       //      * 4. Save file to disk

        Path filePath = uploadDir.resolve(fileName);
        File targetFile = filePath.toFile();

        try{
            file.transferTo(targetFile);
        }catch (Exception e){
            throw new RuntimeException("Cannot upload file", e);
        }

        return new FileUploadResponseDTO(
                fileName, targetFile.getAbsolutePath()
        );
    }


}
