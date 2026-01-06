package com.example.rideshare.dto;

public class FileUploadResponseDTO {

    private String fileName;
    private String location;

    public FileUploadResponseDTO(String fileName, String location) {
        this.fileName = fileName;
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
