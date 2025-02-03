package com.shop.product_service.dto.response.category;

public class CreateCategoryResponse {

    private Long id;

    private String name;

    public CreateCategoryResponse() {
    }

    public CreateCategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
