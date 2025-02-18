package com.shop.order_service.kafka.producer;

import events.order.CreateOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private static final Logger log = LoggerFactory.getLogger(PaymentProducer.class);
    private final KafkaTemplate<String, CreateOrderEvent> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, CreateOrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.template.order-created-topic}")
    private String createOrderTopic;

    public void sendMessage(CreateOrderEvent message) {
        System.out.println("Order Event Created Sout: ");
        log.info("Order Event Created Log: ");
        kafkaTemplate.send(createOrderTopic, message);

    }
}
