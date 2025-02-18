package com.shop.product_service.service;

import com.shop.product_service.dto.request.attribute.CreateAttributeRequest;
import com.shop.product_service.dto.request.attribute.UpdateAttributeRequest;
import com.shop.product_service.dto.response.attribute.*;
import com.shop.product_service.response.ApiResponse;

import java.util.List;

public interface AttributeService {
    ApiResponse<CreateAttributeResponse> createAttribute(CreateAttributeRequest request);
    ApiResponse<UpdateAttributeResponse> updateAttribute(Long id, UpdateAttributeRequest request);
    ApiResponse<GetByIdAttributeResponse> getAttributeById(Long id);
    ApiResponse<List<GetAllAttributeResponse>> getAllAttributes();
    ApiResponse<Void> deleteAttribute(Long id);
    ApiResponse<GetAttributeWithProductResponse> getAttributeWithProduct(Long productId);
}
