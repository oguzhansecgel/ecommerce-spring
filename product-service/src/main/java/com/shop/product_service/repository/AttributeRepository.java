package com.shop.product_service.repository;

import com.shop.product_service.dto.response.attribute.GetAttributeWithProductResponse;
import com.shop.product_service.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    List<Attribute> findAllByProductId(Long productId);
}
