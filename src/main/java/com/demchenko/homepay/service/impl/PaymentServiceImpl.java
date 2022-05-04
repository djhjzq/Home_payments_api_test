package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.PaymentRegistryForm;
import com.demchenko.homepay.entity.Payment;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.repository.PaymentRepository;
import com.demchenko.homepay.service.InvoiceService;
import com.demchenko.homepay.service.PaymentService;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final InvoiceService invoiceService;

    private final UserService userService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, InvoiceService invoiceService, UserService userService) {
        this.paymentRepository = paymentRepository;
        this.invoiceService = invoiceService;
        this.userService = userService;
    }

    @Override
    public void createPayment(Long invoiceId, Long userId, BigDecimal amount) {
        Date date = new Date();
        Payment payment = new Payment();
        User user = userService.findUserById(userId);
        user.setBalance(user.getBalance().add(amount));
        payment.setCreatedOn(date);
        payment.setInvoice(invoiceService.findInvoiceById(invoiceId));
        payment.setUser(user);
        payment.setAmount(amount);
        paymentRepository.save(payment);
    }

    @Override
    public void registryPayment(PaymentRegistryForm paymentRequest) {
        createPayment(paymentRequest.userId(), paymentRequest.invoiceId(),
                paymentRequest.amount());
    }

    @Override
    public Set<Payment> showAllPaymentsByUser(Long userId) {
        return userService.findUserById(userId).getPaymentSet();
    }
}
