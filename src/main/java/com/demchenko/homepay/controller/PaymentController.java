package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.PaymentRegistryForm;
import com.demchenko.homepay.dto.response.PaymentResponse;
import com.demchenko.homepay.mapper.PaymentMapper;
import com.demchenko.homepay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/payments")
@PreAuthorize("hasRole('ROLE_USER')")
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<PaymentResponse> registryPayment(@Valid PaymentRegistryForm paymentRequest) {
        return new ResponseEntity<>(paymentMapper.paymentToPaymentDto(paymentService.registryPayment(paymentRequest)),
                HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<Set<PaymentResponse>> showAllPayments(@Positive Long userId) {
        return new ResponseEntity<>(paymentService.showAllPaymentsByUser(userId).stream()
                .map(paymentMapper:: paymentToPaymentDto).collect(Collectors.toSet()), HttpStatus.OK);
    }
}
