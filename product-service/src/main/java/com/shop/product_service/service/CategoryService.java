package com.shop.product_service.service;

import com.shop.product_service.dto.request.category.CreateCategoryRequest;
import com.shop.product_service.dto.request.category.UpdateCategoryRequest;
import com.shop.product_service.dto.response.category.CreateCategoryResponse;
import com.shop.product_service.dto.response.category.GetAllCategoryResponse;
import com.shop.product_service.dto.response.category.GetByIdCategoryResponse;
import com.shop.product_service.dto.response.category.UpdateCategoryResponse;
import com.shop.product_service.response.ApiResponse;

import java.util.List;

public interface CategoryService {

    ApiResponse<CreateCategoryResponse> createCategory(CreateCategoryRequest request);
    ApiResponse<UpdateCategoryResponse> updateCategory(UpdateCategoryRequest request, Long id);
    ApiResponse<List<GetAllCategoryResponse>> getAllCategory();
    ApiResponse<GetByIdCategoryResponse> getCategoryById(Long id);
    ApiResponse<Void> deleteCategory(Long id);
}
