package com.shop.payment_service.model;

import java.math.BigDecimal;

public class Payment {
    private int cardNumber;
    private int cvv;
    private String orderId;
    private int customerId;
    private BigDecimal amount;

    public Payment() {
    }

    public Payment(int cardNumber, int cvv, String orderId, int customerId, BigDecimal amount) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
