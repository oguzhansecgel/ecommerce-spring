package com.shop.product_service.service.impl;

import com.shop.product_service.dto.request.category.CreateCategoryRequest;
import com.shop.product_service.dto.request.category.UpdateCategoryRequest;
import com.shop.product_service.dto.response.category.CreateCategoryResponse;
import com.shop.product_service.dto.response.category.GetAllCategoryResponse;
import com.shop.product_service.dto.response.category.GetByIdCategoryResponse;
import com.shop.product_service.dto.response.category.UpdateCategoryResponse;
import com.shop.product_service.entity.Category;
import com.shop.product_service.mapper.CategoryMapper;
import com.shop.product_service.repository.CategoryRepository;
import com.shop.product_service.response.ApiResponse;
import com.shop.product_service.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }
    @Override
    public ApiResponse<CreateCategoryResponse> createCategory(CreateCategoryRequest request) {
        try {
            Category category = CategoryMapper.dtoToCreateCategory(request);
            Category savedCategory = repository.save(category);
            CreateCategoryResponse response = CategoryMapper.dtoToCreateCategoryResponse(savedCategory);
            return new ApiResponse<>(HttpStatus.OK, "Category created successfully", response, null);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Failed to create category", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<UpdateCategoryResponse> updateCategory(UpdateCategoryRequest request, Long id) {
        try {
            Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
            category.setName(request.getName());
            Category savedCategory = repository.save(category);
            UpdateCategoryResponse response = CategoryMapper.dtoToUpdateCategoryResponse(savedCategory);
            return new ApiResponse<>(HttpStatus.OK, "Category updated successfully", response, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Failed to update category", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<List<GetAllCategoryResponse>> getAllCategory() {
        List<Category> categories = repository.findAll();
        List<GetAllCategoryResponse> response = CategoryMapper.dtToGetAllCategoryResponse(categories);
        return new ApiResponse<>(HttpStatus.OK, "All categories fetched successfully", response, null);
    }

    @Override
    public ApiResponse<GetByIdCategoryResponse> getCategoryById(Long id) {
        try {
            Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
            GetByIdCategoryResponse response = CategoryMapper.dtoGetByIdCategoryResponse(category);
            return new ApiResponse<>(HttpStatus.OK, "Category found", response, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Category not found", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<Void> deleteCategory(Long id) {
        try {
            Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
            repository.delete(category);
            return new ApiResponse<>(HttpStatus.OK, "Category deleted successfully", null, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Failed to delete category", null, List.of(e.getMessage()));
        }
    }
}
