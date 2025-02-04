package com.shop.product_service.repository;

import com.shop.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllBySubCategoryId(Long subCategoryId);
}
