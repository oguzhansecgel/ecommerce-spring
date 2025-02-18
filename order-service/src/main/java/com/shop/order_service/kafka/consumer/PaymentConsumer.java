package com.shop.order_service.kafka.consumer;

import com.shop.order_service.service.OrderService;
import events.order.CreateOrderEvent;
import events.payment.PaymentFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private final OrderService orderService;
    private Logger log = LoggerFactory.getLogger(PaymentConsumer.class);

    public PaymentConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "${spring.kafka.template.payment-failed-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(PaymentFailedEvent event)
    {
        log.info("Order Created Event Consume"+event);
        orderService.deleteOrder(event.getOrderId());

    }
}
