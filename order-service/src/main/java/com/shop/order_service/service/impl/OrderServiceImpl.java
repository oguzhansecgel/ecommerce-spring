package com.shop.order_service.service.impl;

import com.shop.order_service.client.BasketClient;
import com.shop.order_service.model.Basket;
import com.shop.order_service.model.Order;
import com.shop.order_service.repository.OrderRepository;
import com.shop.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketClient basketClient;

    public OrderServiceImpl(OrderRepository orderRepository, BasketClient basketClient) {
        this.orderRepository = orderRepository;
        this.basketClient = basketClient;
    }

    @Override
    public Order createOrder(String basketId) {
        Basket basket = basketClient.findByBasketId(basketId);
        System.out.println("Basket items: " + basket.getBasketItems());
        Order order = new Order();
        order.setCustomerId(basket.getCustomerId());
        order.setBasketItems(basket.getBasketItems());
        order.setTotalPrice(basket.getTotalPrice());
        order.setStatus("PENDING");
        order.setBasketId(basketId);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return List.of();
    }

    @Override
    public Optional<Order> getByIdOrder(String orderId) {
        return Optional.empty();
    }

    @Override
    public List<Order> getOrderHistoryForCustomer(Integer customerId) {
        List<Order> order = orderRepository.findByCustomerId(customerId);
        return order;
    }
}
