package com.shop.order_service.service.impl;

import com.shop.order_service.client.BasketClient;
import com.shop.order_service.kafka.producer.PaymentProducer;
import com.shop.order_service.model.Basket;
import com.shop.order_service.model.Order;
import com.shop.order_service.repository.OrderRepository;
import com.shop.order_service.service.OrderService;
import events.order.CreateOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketClient basketClient;
    private final PaymentProducer paymentProducer;
    private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    public OrderServiceImpl(OrderRepository orderRepository, BasketClient basketClient, PaymentProducer paymentProducer) {
        this.orderRepository = orderRepository;
        this.basketClient = basketClient;
        this.paymentProducer = paymentProducer;
    }

    @Override
    public Order createOrder(String basketId) {
        Basket basket = basketClient.findByBasketId(basketId);
        Order order = new Order();
        order.setCustomerId(basket.getCustomerId());
        order.setBasketItems(basket.getBasketItems());
        order.setTotalPrice(basket.getTotalPrice());
        order.setStatus("PENDING");
        order.setBasketId(basketId);
        Order savedOrder = orderRepository.save(order);
        CreateOrderEvent createOrderEvent = new CreateOrderEvent();
        createOrderEvent.setId(savedOrder.getId());
        createOrderEvent.setCustomerId(savedOrder.getCustomerId());
        createOrderEvent.setAmount(savedOrder.getTotalPrice());
        paymentProducer.sendMessage(createOrderEvent);
        return savedOrder;
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

    @Override
    public void deleteOrder(String id) {
        log.info("Deleting order with id {}", id);
        orderRepository.deleteById(id);
    }

    @Override
    public void updateStatusOrder(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent())
        {
            log.info("Updating status of order with id {}", orderId);
            order.get().setStatus("COMPLETED");
            orderRepository.save(order.get());
        }
    }
}
