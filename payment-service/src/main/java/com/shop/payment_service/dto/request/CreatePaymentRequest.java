package com.shop.payment_service.dto.request;

public class CreatePaymentRequest {
    private int cardNumber;
    private int cvv;
    private String orderId;
    public CreatePaymentRequest() {
    }

    public CreatePaymentRequest(int cardNumber, int cvv, String orderId) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
