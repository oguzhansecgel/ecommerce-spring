package com.shop.order_service.model;

import java.math.BigDecimal;

public class ProductDto {
    private String name;
    private BigDecimal price;
    private String description;
    private int stock;
    private Long subCategoryId;

    public ProductDto() {
    }

    public ProductDto(String name, BigDecimal price, String description, int stock, Long subCategoryId) {
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
