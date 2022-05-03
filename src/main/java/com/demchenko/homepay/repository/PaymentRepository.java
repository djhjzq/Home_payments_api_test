package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}