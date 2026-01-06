package com.example.rideshare.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FileNameUtil {

    public static String getFileName(String originalFileName) {

        // file --> image.jpg ---> hghgejdhejhimage.jpg
        if(originalFileName==null || originalFileName.isEmpty()){
            throw new IllegalArgumentException("original filename cannot be null or empty");
        }

        // THe extension of file  imaage123.jpg

        int index = originalFileName.lastIndexOf('.');

        if(index==-1){

            return UUID.randomUUID().toString()+"."+originalFileName;
        }

        String ext = originalFileName.substring(index+1);
        return UUID.randomUUID().toString()+"-"+originalFileName+"."+ext;
    }
}
