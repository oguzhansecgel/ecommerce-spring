package com.shop.order_service.kafka.consumer;

import com.shop.order_service.service.OrderService;
import events.order.CreateOrderEvent;
import events.payment.PaymentFailedEvent;
import events.payment.PaymentSuccessEvent;
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
    public void paymentFailedConsume(PaymentFailedEvent event)
    {
        log.info("Payment Failed Order Deleted"+event);
        orderService.deleteOrder(event.getOrderId());

    }
    @KafkaListener(topics = "${spring.kafka.template.payment-success-topic}", groupId = "${spring.kafka.consumer.group-id}",containerFactory = "paymentSuccessEventConcurrentKafkaListenerContainerFactory")
    public void PaymentSuccessConsume(PaymentSuccessEvent event)
    {
        log.info("Payment Successful Order Type Changed to Completed"+event);
        orderService.updateStatusOrder(event.getOrderId());

    }

}
