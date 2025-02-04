package com.shop.product_service.service;

import com.shop.product_service.dto.request.attribute.CreateAttributeRequest;
import com.shop.product_service.dto.request.attribute.UpdateAttributeRequest;
import com.shop.product_service.dto.response.attribute.*;

import java.util.List;

public interface AttributeService {
    CreateAttributeResponse createAttribute(CreateAttributeRequest request);
    UpdateAttributeResponse updateAttribute(Long id, UpdateAttributeRequest request);
    GetByIdAttributeResponse getAttributeById(Long id);
    List<GetAllAttributeResponse> getAllAttributes();
    void deleteAttribute(Long id);
    GetAttributeWithProductResponse getAttributeWithProduct(Long id);
}
