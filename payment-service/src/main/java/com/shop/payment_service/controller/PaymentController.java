package com.shop.payment_service.controller;

import com.shop.payment_service.dto.request.CreatePaymentRequest;
import com.shop.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class  PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/payment")
    public void createPayment(@RequestBody CreatePaymentRequest request)
    {
        paymentService.processPayment(request);
    }
}
