package com.tastytown.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.service.impl.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Category API", description = "This Controller manages CRUD operation for Category Entity ")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Category Created")
    @Operation(summary = "Create a new Category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        // var saveCategory = categoryService.saveCategory(requestDTO);

        return new ResponseEntity<>(categoryService.saveCategory(requestDTO), HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Extract all Categories")
    @ApiResponse(responseCode = "200", description = "Category Extracted")
    // public List<Category> getEntity(CategoryService categoryService) {
    //     // var saveCategory = categoryService.saveCategory(requestDTO);
    //     for (Category category : getEntity(categoryService)) {    
    //     }
    //     return getEntity(categoryService);
    // }
    public ResponseEntity<List<Category>> findAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    @ApiResponse(description = "Get Category by id")
    @Operation(summary = "Extract Category by category id")
    public ResponseEntity<Category> getCategoryById(@PathVariable String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }
    
    @PutMapping("/{categoryId}")
    @ApiResponse(description = "Update a category by id")
    @Operation(summary = "update a category by category id")
    public ResponseEntity<Category> updateCategory(@PathVariable String categoryId,
                                @RequestBody CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryId, requestDTO)); 
                                }
                                
    // @DeleteMapping("/{categoryId}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // @ApiResponse(description = "Category deleted successfully")
    // @Operation(summary = "Delete a category by id")
    // public ResponseEntity<Category> deleteCategory(@PathVariable String categoryId) {
    //     categoryService.deleteCategoryById(categoryId);
    //     return ResponseEntity.noContent().build();
    // }

    @DeleteMapping("/{categoryId}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "Category deleted successfully")
    @Operation(summary = "Delete a category by category id")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
