package com.tastytown.backend.mapper;

import com.tastytown.backend.dto.FoodRequestDTO;
import com.tastytown.backend.dto.FoodResponseDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.entity.Food;

public class FoodMapper {
    private FoodMapper() {
        
    }

    public static Food convertToEntity(FoodRequestDTO requestDTO, Category existingCategory, String fileName) {
        return Food.builder()
        .foodName(requestDTO.foodName())
        .foodDescription(requestDTO.foodDescription())
        .foodPrice(requestDTO.foodPrice())
        .foodImage(fileName)
        .category(existingCategory)
        .build();
    }

    public static FoodResponseDTO convertToDTO(Food saveFood) {
        return new FoodResponseDTO(
            saveFood.getFoodId(),
            saveFood.getFoodName(),
            saveFood.getFoodDescription(),
            saveFood.getFoodPrice(),
            saveFood.getFoodImage(),
            saveFood.getCategory().getCategoryId(),
            saveFood.getCategory().getCategoryName());
    }
}
