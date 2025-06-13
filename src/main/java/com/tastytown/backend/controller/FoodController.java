package com.tastytown.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tastytown.backend.dto.FoodRequestDTO;
import com.tastytown.backend.dto.FoodResponseDTO;
import com.tastytown.backend.service.impl.FoodServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
@Tag(name = "Food API", description = "This controller manages CRUD operations for Food Entity")
public class FoodController {
    private final FoodServiceImpl foodServiceImpl;
    private final ObjectMapper objectMapper;
    
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Food created")
    @Operation(summary = "Create a new Food")
    public ResponseEntity<FoodResponseDTO> saveFood(@RequestPart String rawJson, 
                                @RequestPart MultipartFile foodImage) throws IOException{

            FoodRequestDTO requestDTO = objectMapper.readValue(rawJson, FoodRequestDTO.class);

            return new ResponseEntity<>(foodServiceImpl.createFood(requestDTO, foodImage), HttpStatus.CREATED);

    }
}
