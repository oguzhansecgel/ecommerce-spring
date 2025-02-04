package com.shop.product_service.mapper;

import com.shop.product_service.dto.request.attribute.CreateAttributeRequest;
import com.shop.product_service.dto.request.attribute.UpdateAttributeRequest;
import com.shop.product_service.dto.response.attribute.*;
import com.shop.product_service.entity.Attribute;
import com.shop.product_service.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AttributeMapper {

    public static Attribute dtoToCreateAttribute(CreateAttributeRequest request, Product product) {
        Attribute attribute = new Attribute();
        attribute.setKey(request.getKey());
        attribute.setValue(request.getValue());
        attribute.setProduct(product);
        return attribute;
    }

    public static Attribute dtoToUpdateAttribute(UpdateAttributeRequest request, Product product) {
        Attribute attribute = new Attribute();
        attribute.setKey(request.getKey());
        attribute.setValue(request.getValue());
        attribute.setProduct(product);
        return attribute;
    }

    public static CreateAttributeResponse dtoToCreateAttributeResponse(Attribute attribute) {
        return new CreateAttributeResponse(
                attribute.getId(),
                attribute.getKey(),
                attribute.getValue(),
                attribute.getProduct().getId()
        );
    }

    public static UpdateAttributeResponse dtoToUpdateAttributeResponse(Attribute attribute) {
        return new UpdateAttributeResponse(
                attribute.getId(),
                attribute.getKey(),
                attribute.getValue(),
                attribute.getProduct().getId()
        );
    }

    public static GetByIdAttributeResponse dtoToGetByIdAttributeResponse(Attribute attribute) {
        return new GetByIdAttributeResponse(
                attribute.getId(),
                attribute.getKey(),
                attribute.getValue(),
                attribute.getProduct().getId()
        );
    }

    public static List<GetAllAttributeResponse> dtoToGetAllAttributesResponse(List<Attribute> attributes) {
        return attributes.stream()
                .map(AttributeMapper::toGetAllAttributeResponse)
                .collect(Collectors.toList());
    }

    public static GetAllAttributeResponse toGetAllAttributeResponse(Attribute attribute) {
        return new GetAllAttributeResponse(
                attribute.getId(),
                attribute.getKey(),
                attribute.getValue(),
                attribute.getProduct().getId()
        );
    }

    public static GetAttributeWithProductResponse getAttributeWithProduct(Product product, List<Attribute> attributes)
    {
        Map<String, String> attributeMap = attributes.stream()
                .collect(Collectors.toMap(Attribute::getKey, Attribute::getValue));

        return new GetAttributeWithProductResponse(
                product.getName(),
                product.getPrice(),
                attributeMap
        );

    }
}
