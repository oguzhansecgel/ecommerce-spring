package com.shop.product_service.dto.request.subcategory;

public class UpdateSubCategoryRequest {
    private String name;

    private Long categoryId;

    public UpdateSubCategoryRequest(String name, Long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public UpdateSubCategoryRequest() {
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
