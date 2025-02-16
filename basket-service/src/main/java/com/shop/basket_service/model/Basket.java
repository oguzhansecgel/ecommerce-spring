package com.shop.basket_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "customer_baskets")
public class Basket {
    @Id
    private String id;
    private String customerId;
    private List<BasketItem> basketItems;
    private BigDecimal totalPrice;



    public Basket() {
    }

    public Basket(String id, String customerId, List<BasketItem> basketItems, BigDecimal totalPrice) {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
