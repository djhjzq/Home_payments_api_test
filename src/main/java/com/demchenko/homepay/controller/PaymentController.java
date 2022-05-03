package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.PaymentRequest;
import com.demchenko.homepay.dto.response.PaymentDto;
import com.demchenko.homepay.mapper.PaymentMapper;
import com.demchenko.homepay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @PostMapping("/new")
    public void registryPayment(PaymentRequest paymentRequest) {
        paymentService.registryPayment(paymentRequest);
    }

    @PostMapping("/all")
    public Set<PaymentDto> showAllPayments(Long userId) {
        return paymentService.showAllPaymentsByUser(userId).stream()
                .map(paymentMapper:: paymentToPaymentDto).collect(Collectors.toSet());
    }
}
