package com.shop.product_service.service;

import com.shop.product_service.dto.request.category.CreateCategoryRequest;
import com.shop.product_service.dto.request.category.UpdateCategoryRequest;
import com.shop.product_service.dto.response.category.CreateCategoryResponse;
import com.shop.product_service.dto.response.category.GetAllCategoryResponse;
import com.shop.product_service.dto.response.category.GetByIdCategoryResponse;
import com.shop.product_service.dto.response.category.UpdateCategoryResponse;

import java.util.List;

public interface CategoryService {

    CreateCategoryResponse createCategory(CreateCategoryRequest request);
    UpdateCategoryResponse updateCategory(UpdateCategoryRequest request, Long id);
    List<GetAllCategoryResponse> getAllCategory();
    GetByIdCategoryResponse getCategoryById(Long id);
    void deleteCategory(Long id);
}
