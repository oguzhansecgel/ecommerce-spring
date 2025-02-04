package com.shop.product_service.service.impl;

import com.shop.product_service.dto.request.attribute.CreateAttributeRequest;
import com.shop.product_service.dto.request.attribute.UpdateAttributeRequest;
import com.shop.product_service.dto.response.attribute.*;
import com.shop.product_service.entity.Attribute;
import com.shop.product_service.entity.Product;
import com.shop.product_service.mapper.AttributeMapper;
import com.shop.product_service.repository.AttributeRepository;
import com.shop.product_service.repository.ProductRepository;
import com.shop.product_service.service.AttributeService;
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
    public CreateAttributeResponse createAttribute(CreateAttributeRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Attribute attribute = AttributeMapper.dtoToCreateAttribute(request, product);
        Attribute savedAttribute = attributeRepository.save(attribute);

        return AttributeMapper.dtoToCreateAttributeResponse(savedAttribute);
    }

    @Override
    public UpdateAttributeResponse updateAttribute(Long id, UpdateAttributeRequest request) {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attribute not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        attribute.setKey(request.getKey());
        attribute.setValue(request.getValue());
        attribute.setProduct(product);

        Attribute updatedAttribute = attributeRepository.save(attribute);
        return AttributeMapper.dtoToUpdateAttributeResponse(updatedAttribute);
    }

    @Override
    public GetByIdAttributeResponse getAttributeById(Long id) {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attribute not found"));

        return AttributeMapper.dtoToGetByIdAttributeResponse(attribute);
    }

    @Override
    public List<GetAllAttributeResponse> getAllAttributes() {
        List<Attribute> attributes = attributeRepository.findAll();
        return AttributeMapper.dtoToGetAllAttributesResponse(attributes);
    }

    @Override
    public void deleteAttribute(Long id) {
        if (!attributeRepository.existsById(id)) {
            throw new RuntimeException("Attribute not found");
        }
        attributeRepository.deleteById(id);
    }

    @Override
    public GetAttributeWithProductResponse getAttributeWithProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<Attribute> attributes = attributeRepository.findAllByProductId(productId);

        return AttributeMapper.getAttributeWithProduct(product, attributes);
    }
}
