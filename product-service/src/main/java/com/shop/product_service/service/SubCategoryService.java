package com.shop.product_service.service;

import com.shop.product_service.dto.request.subcategory.CreateSubCategoryRequest;
import com.shop.product_service.dto.request.subcategory.UpdateSubCategoryRequest;
import com.shop.product_service.dto.response.subcategory.CreateSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetAllSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetByIdSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.UpdateSubCategoryResponse;
import com.shop.product_service.entity.SubCategory;
import com.shop.product_service.response.ApiResponse;

import java.util.List;

public interface SubCategoryService {
    ApiResponse<CreateSubCategoryResponse> createSubCategory(CreateSubCategoryRequest request);
    ApiResponse<UpdateSubCategoryResponse> updateSubCategory(UpdateSubCategoryRequest request, Long id);
    ApiResponse<List<GetAllSubCategoryResponse>> getAllSubCategory();
    ApiResponse<GetByIdSubCategoryResponse> getSubCategoryById(Long id);
    ApiResponse<Void> deleteSubCategory(Long id);
    ApiResponse<List<GetAllSubCategoryResponse>> subCategoryWithCategory(Long categoryId);
}
