package com.tastytown.backend.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

public interface IImageService {
    ResponseEntity<byte[]> extractFoodImage(String foodImage) throws IOException;
    //byte[] extractFoodImage(String foodImage);
}
