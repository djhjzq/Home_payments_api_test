package com.demchenko.homepay.service;

import com.demchenko.homepay.dto.request.PaymentRegistryForm;
import com.demchenko.homepay.entity.Payment;

import java.math.BigDecimal;
import java.util.Set;

public interface PaymentService {

    void createPayment(Long invoiceId, Long userId, BigDecimal amount);

    void registryPayment(PaymentRegistryForm paymentRequest);

    Set<Payment> showAllPaymentsByUser(Long userId);

}
