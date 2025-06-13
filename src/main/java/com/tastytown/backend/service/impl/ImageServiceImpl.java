package com.tastytown.backend.service.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.tastytown.backend.service.IImageService;

@Service
public class ImageServiceImpl implements IImageService {

    @Value("${upload.file.dir}")
    private String FILE_DIR;
 @Override
 public ResponseEntity<byte[]> extractFoodImage(String foodImage) throws IOException {
        if(foodImage == null || foodImage.isEmpty()) {
            throw new NoSuchElementException("Image not found.");
        }

        var file = new File(FILE_DIR + File.separator + foodImage);
        if(!file.exists()) {
            throw new FileNotFoundException("File not found with name " + foodImage + ".");
        }

        var fis = new FileInputStream(file);
        var image = fis.readAllBytes();
        fis.close();

        var lowerFoodImageName = foodImage.toLowerCase();
        String contentType = "" ;
        if(lowerFoodImageName.endsWith(".jpg") || lowerFoodImageName.endsWith(".jpeg")) {
            contentType = MediaType.IMAGE_JPEG_VALUE;
        } else if(lowerFoodImageName.endsWith(".png")) {
            contentType = MediaType.IMAGE_PNG_VALUE;
        } else if(lowerFoodImageName.endsWith(".gif")) {
            contentType = MediaType.IMAGE_GIF_VALUE;
        }

        return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(image);

    }
}
    
