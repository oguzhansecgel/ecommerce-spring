package com.shop.product_service.service.impl;

import com.shop.product_service.dto.request.attribute.CreateAttributeRequest;
import com.shop.product_service.dto.request.attribute.UpdateAttributeRequest;
import com.shop.product_service.dto.response.attribute.*;
import com.shop.product_service.entity.Attribute;
import com.shop.product_service.entity.Product;
import com.shop.product_service.mapper.AttributeMapper;
import com.shop.product_service.repository.AttributeRepository;
import com.shop.product_service.repository.ProductRepository;
import com.shop.product_service.response.ApiResponse;
import com.shop.product_service.service.AttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final ProductRepository productRepository;

    public AttributeServiceImpl(AttributeRepository attributeRepository, ProductRepository productRepository) {
        this.attributeRepository = attributeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ApiResponse<CreateAttributeResponse> createAttribute(CreateAttributeRequest request) {
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            Attribute attribute = AttributeMapper.dtoToCreateAttribute(request, product);
            Attribute savedAttribute = attributeRepository.save(attribute);

            return new ApiResponse<>(HttpStatus.CREATED, "Attribute created successfully",
                    AttributeMapper.dtoToCreateAttributeResponse(savedAttribute), null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Error creating attribute", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<UpdateAttributeResponse> updateAttribute(Long id, UpdateAttributeRequest request) {
        try {
            Attribute attribute = attributeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Attribute not found"));

            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            attribute.setKey(request.getKey());
            attribute.setValue(request.getValue());
            attribute.setProduct(product);

            Attribute updatedAttribute = attributeRepository.save(attribute);
            return new ApiResponse<>(HttpStatus.OK, "Attribute updated successfully",
                    AttributeMapper.dtoToUpdateAttributeResponse(updatedAttribute), null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Error updating attribute", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<GetByIdAttributeResponse> getAttributeById(Long id) {
        try {
            Attribute attribute = attributeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Attribute not found"));
            return new ApiResponse<>(HttpStatus.OK, "Attribute found",
                    AttributeMapper.dtoToGetByIdAttributeResponse(attribute), null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Attribute not found", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<List<GetAllAttributeResponse>> getAllAttributes() {
        List<Attribute> attributes = attributeRepository.findAll();
        return new ApiResponse<>(HttpStatus.OK, "Attributes fetched successfully",
                AttributeMapper.dtoToGetAllAttributesResponse(attributes), null);
    }

    @Override
    public ApiResponse<Void> deleteAttribute(Long id) {
        try {
            if (!attributeRepository.existsById(id)) {
                throw new RuntimeException("Attribute not found");
            }
            attributeRepository.deleteById(id);
            return new ApiResponse<>(HttpStatus.NO_CONTENT, "Attribute deleted successfully", null, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Error deleting attribute", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<GetAttributeWithProductResponse> getAttributeWithProduct(Long productId) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            List<Attribute> attributes = attributeRepository.findAllByProductId(productId);
            return new ApiResponse<>(HttpStatus.OK, "Attributes with product found",
                    AttributeMapper.getAttributeWithProduct(product, attributes), null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Product or attributes not found", null, List.of(e.getMessage()));
        }
    }
}
