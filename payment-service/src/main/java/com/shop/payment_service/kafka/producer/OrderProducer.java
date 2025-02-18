package com.shop.payment_service.kafka.producer;

import events.order.CreateOrderEvent;
import events.payment.PaymentFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);
    private final KafkaTemplate<String, PaymentFailedEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, PaymentFailedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.template.payment-failed-topic}")
    private String createOrderTopic;

    public void sendMessage(PaymentFailedEvent message) {

        log.info("Payment Event Failed Log: ");
        kafkaTemplate.send(createOrderTopic, message);

    }
}
