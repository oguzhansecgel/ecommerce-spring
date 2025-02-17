package com.shop.order_service.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private String id;
    private int customerId;
    private List<BasketItemDto> basketItems = new ArrayList<>();
    private BigDecimal totalPrice;

    public Basket() {
    }

    public Basket(String id, int customerId, List<BasketItemDto> basketItems, BigDecimal totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.basketItems = basketItems;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<BasketItemDto> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItemDto> basketItems) {
        this.basketItems = basketItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
