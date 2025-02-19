package com.shop.payment_service.service;

import com.shop.payment_service.dto.request.CreatePaymentRequest;
import com.shop.payment_service.kafka.producer.OrderProducer;
import com.shop.payment_service.model.Payment;
import events.order.CreateOrderEvent;
import events.payment.PaymentFailedEvent;
import events.payment.PaymentSuccessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private CreateOrderEvent cachedOrderEvent;
    private final OrderProducer orderProducer;
    private Logger log = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    public void processOrder(CreateOrderEvent createOrderEvent) {
        log.info("Order Received: " + createOrderEvent);
        cachedOrderEvent = createOrderEvent;
    }
    public void processPayment(CreatePaymentRequest request)
    {
        Payment payment = new Payment();
        try {
            payment.setCvv(request.getCvv());
            payment.setCardNumber(request.getCardNumber());
            payment.setOrderId(request.getOrderId());
            payment.setAmount(cachedOrderEvent.getAmount());
            boolean paymentSuccess = paymentStatus();
            if(paymentSuccess)
            {
                PaymentSuccessEvent paymentSuccessEvent = new PaymentSuccessEvent();
                paymentSuccessEvent.setOrderId(payment.getOrderId());
                orderProducer.paymentSuccessSendMessage(paymentSuccessEvent);
            }
            else
            {
                log.info("Ödeme adımında bir hata oluştu. Sipariş iptal edildi.");
                PaymentFailedEvent event = new PaymentFailedEvent();
                event.setOrderId(request.getOrderId());
                orderProducer.paymentFailSendMessage(event);
            }
        }catch (Exception e)
        {
            System.out.println("error"+e);
        }

    }
    private boolean paymentStatus()
    {
       return true;
    }

}
