package com.shop.order_service.controller;

import com.shop.order_service.model.Order;
import com.shop.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/customer/order/history/{customerId}")
    public List<Order> customerOrderHistory(@PathVariable Integer customerId)
    {
        return orderService.getOrderHistoryForCustomer(customerId);
    }
    @PostMapping("/create-order/{basketId}")
    public ResponseEntity<Order> createOrder(@PathVariable String basketId) {
        Order order = orderService.createOrder(basketId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
