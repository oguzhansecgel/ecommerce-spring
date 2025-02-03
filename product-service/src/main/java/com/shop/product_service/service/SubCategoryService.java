package com.shop.product_service.service;

import com.shop.product_service.dto.request.subcategory.CreateSubCategoryRequest;
import com.shop.product_service.dto.request.subcategory.UpdateSubCategoryRequest;
import com.shop.product_service.dto.response.subcategory.CreateSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetAllSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetByIdSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.UpdateSubCategoryResponse;

import java.util.List;

public interface SubCategoryService {

    CreateSubCategoryResponse createSubCategory(CreateSubCategoryRequest request);
    UpdateSubCategoryResponse updateSubCategory(UpdateSubCategoryRequest request,Long id);
    List<GetAllSubCategoryResponse> getAllSubCategory();
    GetByIdSubCategoryResponse getSubCategoryById(Long id);
    void deleteSubCategory(Long id);
}
