package com.shop.order_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private int customerId;
    private List<BasketItemDto> basketItems = new ArrayList<>();
    private BigDecimal totalPrice;
    private String status;
    private String basketId;

    public Order() {
    }

    public Order(String id, int customerId, List<BasketItemDto> basketItems, BigDecimal totalPrice, String status, String basketId) {
        this.id = id;
        this.customerId = customerId;
        this.basketItems = basketItems;
        this.totalPrice = totalPrice;
        this.status = status;
        this.basketId = basketId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }
}