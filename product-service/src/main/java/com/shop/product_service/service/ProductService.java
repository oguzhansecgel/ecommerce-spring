package com.shop.product_service.service;

import com.shop.product_service.dto.request.product.CreateProductRequest;
import com.shop.product_service.dto.request.product.UpdateProductRequest;
import com.shop.product_service.dto.response.product.CreateProductResponse;
import com.shop.product_service.dto.response.product.GetAllProductResponse;
import com.shop.product_service.dto.response.product.GetByIdProductResponse;
import com.shop.product_service.dto.response.product.UpdateProductResponse;
import com.shop.product_service.response.ApiResponse;

import java.util.List;

public interface ProductService {
    ApiResponse<CreateProductResponse> createProduct(CreateProductRequest request);
    ApiResponse<UpdateProductResponse> updateProduct(Long productId, UpdateProductRequest request);
    ApiResponse<Void> deleteProduct(Long productId);
    ApiResponse<GetByIdProductResponse> getProductById(Long productId);
    ApiResponse<List<GetAllProductResponse>> getAllProducts();
    ApiResponse<List<GetAllProductResponse>> productWithSubCategory(Long subCategoryId);
}
