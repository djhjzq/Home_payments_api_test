package com.demchenko.homepay.service;

import com.demchenko.homepay.dto.request.PaymentRequest;
import com.demchenko.homepay.entity.Payment;

import java.math.BigDecimal;
import java.util.Set;

public interface PaymentService {

    void createPayment(Long invoiceId, Long userId, BigDecimal amount);

    void registryPayment(PaymentRequest paymentRequest);

    Set<Payment> showAllPaymentsByUser(Long userId);

}
