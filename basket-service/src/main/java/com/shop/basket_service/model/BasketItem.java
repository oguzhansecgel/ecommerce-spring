package com.shop.basket_service.model;

import java.math.BigDecimal;

public class BasketItem {
    private ProductClientDto productClientDto;
    private int quantity;
    private BigDecimal totalPrice;

    public BasketItem() {
    }

    public BasketItem(ProductClientDto productClientDto, int quantity, BigDecimal totalPrice) {
        this.productClientDto = productClientDto;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public ProductClientDto getProduct() {
        return productClientDto;
    }

    public void setProduct(ProductClientDto productClientDto) {
        this.productClientDto = productClientDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
