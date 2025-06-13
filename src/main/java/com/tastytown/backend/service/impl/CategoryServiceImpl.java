package com.tastytown.backend.service.impl;

import java.util.List;

// import java.util.Locale.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.exception.CategoryNotFoundException;
import com.tastytown.backend.repository.CategoryRepository;
import com.tastytown.backend.service.ICategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public Category saveCategory(CategoryRequestDTO requestDTO) {
        var category = Category.builder()
                .categoryName(requestDTO.getCategoryName())
                .build();
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
        // throw new UnsupportedOperationException("Unimplemented method 'findAllCategories'");
    }

    public Category getCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("category Not Found with ID:-" + categoryId));
    }

    public Category updateCategoryById(String categoryId, @RequestBody CategoryRequestDTO requestDTO) {
        var existingCategory = getCategoryById(categoryId);
        existingCategory.setCategoryName(requestDTO.getCategoryName());

        return categoryRepository.save(existingCategory);
    }

    public void deleteCategoryById(String categoryId) {
        var existingCategory = getCategoryById(categoryId);
        categoryRepository.delete(existingCategory);
    }
}
