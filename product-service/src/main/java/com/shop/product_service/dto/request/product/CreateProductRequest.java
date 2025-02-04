package com.shop.product_service.dto.request.product;

import java.math.BigDecimal;

public class CreateProductRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private int stock;
    private Long subCategoryId;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String name, BigDecimal price, String description, int stock, Long subCategoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.subCategoryId = subCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
}
