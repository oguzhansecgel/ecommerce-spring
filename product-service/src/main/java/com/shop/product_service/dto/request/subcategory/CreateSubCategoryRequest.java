package com.shop.product_service.dto.request.subcategory;

import com.shop.product_service.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CreateSubCategoryRequest {
    private String name;

    private Long categoryId;

    public CreateSubCategoryRequest(String name, Long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public CreateSubCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
