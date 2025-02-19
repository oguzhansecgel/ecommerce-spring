package com.shop.payment_service.kafka.producer;

import events.order.CreateOrderEvent;
import events.payment.PaymentFailedEvent;
import events.payment.PaymentSuccessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);
    private final KafkaTemplate<String, PaymentFailedEvent> paymentFailedEventKafkaTemplate;
    private final KafkaTemplate<String, PaymentSuccessEvent> paymentSuccessEventKafkaTemplate;

    public OrderProducer(KafkaTemplate<String, PaymentFailedEvent> paymentFailedEventKafkaTemplate, KafkaTemplate<String, PaymentSuccessEvent> paymentSuccessEventKafkaTemplate) {
        this.paymentFailedEventKafkaTemplate = paymentFailedEventKafkaTemplate;
        this.paymentSuccessEventKafkaTemplate = paymentSuccessEventKafkaTemplate;
    }

    @Value("${spring.kafka.template.payment-failed-topic}")
    private String paymentFailedTopic;

    @Value("${spring.kafka.template.payment-success-topic}")
    private String paymentSuccessTopic;

    public void paymentFailSendMessage(PaymentFailedEvent message) {

        log.info("Payment Event Failed Log: ");
        paymentFailedEventKafkaTemplate.send(paymentFailedTopic, message);

    }
    public void paymentSuccessSendMessage(PaymentSuccessEvent message)
    {
        log.info("Payment Event Success Log: ");
        paymentSuccessEventKafkaTemplate.send(paymentSuccessTopic,message);
    }
}
