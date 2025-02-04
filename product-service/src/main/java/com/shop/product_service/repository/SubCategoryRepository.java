package com.shop.product_service.repository;

import com.shop.product_service.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findSubCategoriesByCategory_Id(Long categoryId);

}
