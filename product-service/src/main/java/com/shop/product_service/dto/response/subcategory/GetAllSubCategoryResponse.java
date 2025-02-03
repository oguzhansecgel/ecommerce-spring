package com.shop.product_service.dto.response.subcategory;

public class GetAllSubCategoryResponse {
    private Long id;
    private String name;
    private Long categoryId;

    public GetAllSubCategoryResponse() {
    }

    public GetAllSubCategoryResponse(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
