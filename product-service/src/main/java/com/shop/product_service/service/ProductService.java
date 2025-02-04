package com.shop.product_service.service;

import com.shop.product_service.dto.request.product.CreateProductRequest;
import com.shop.product_service.dto.request.product.UpdateProductRequest;
import com.shop.product_service.dto.response.product.CreateProductResponse;
import com.shop.product_service.dto.response.product.GetAllProductResponse;
import com.shop.product_service.dto.response.product.GetByIdProductResponse;
import com.shop.product_service.dto.response.product.UpdateProductResponse;

import java.util.List;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest request);
    UpdateProductResponse updateProduct(Long productId, UpdateProductRequest request);
    void deleteProduct(Long productId);
    GetByIdProductResponse getProductById(Long productId);
    List<GetAllProductResponse> getAllProducts();
    List<GetAllProductResponse> productWithSubCategory(Long subCategoryId);
}
