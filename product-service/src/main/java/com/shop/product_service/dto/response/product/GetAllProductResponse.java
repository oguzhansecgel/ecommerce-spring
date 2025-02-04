package com.shop.product_service.dto.response.product;

import java.math.BigDecimal;

public class GetAllProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private int stock;
    private Long subCategoryId;

    public GetAllProductResponse() {
    }

    public GetAllProductResponse(Long id, String name, BigDecimal price, String description, int stock, Long subCategoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.subCategoryId = subCategoryId;
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
