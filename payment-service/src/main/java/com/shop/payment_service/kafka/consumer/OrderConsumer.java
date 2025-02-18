package com.shop.payment_service.kafka.consumer;

import com.shop.payment_service.service.PaymentService;
import events.order.CreateOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final PaymentService paymentService;
    private Logger log = LoggerFactory.getLogger(OrderConsumer.class);
    public OrderConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "${spring.kafka.template.order-created-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CreateOrderEvent event)
    {
        log.info("Order Created Event Consume"+event);
        paymentService.processOrder(event);

    }
}
