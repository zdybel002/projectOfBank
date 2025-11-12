package com.zdybel.course.controller;

import com.zdybel.course.dto.payment.PaymentRequestDTO;
import com.zdybel.course.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public Object pay(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return paymentService.pay(paymentRequestDTO.getAccountId(), paymentRequestDTO.getAmount());
    }

    @PostMapping("/deposit")
    public Object deposit(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return paymentService.deposit(paymentRequestDTO.getAccountId(), paymentRequestDTO.getAmount());
    }
}
