package com.shop.product_service.dto.request.category;

public class UpdateCategoryRequest {

    private String name;

    public UpdateCategoryRequest() {
    }

    public UpdateCategoryRequest( String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
