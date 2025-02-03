package com.shop.product_service.dto.request.category;

import com.shop.product_service.entity.Category;

public class CreateCategoryRequest {

    private String name;

    public CreateCategoryRequest() {
    }

    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
