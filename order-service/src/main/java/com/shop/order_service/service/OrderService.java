package com.shop.order_service.service;

import com.shop.order_service.model.Order;
import events.payment.PaymentFailedEvent;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(String basketId);
    List<Order> getAllOrder();
    Optional<Order> getByIdOrder(String orderId);
    List<Order> getOrderHistoryForCustomer(Integer customerId);
    void deleteOrder(String id);
    void updateStatusOrder(String orderId);
}
