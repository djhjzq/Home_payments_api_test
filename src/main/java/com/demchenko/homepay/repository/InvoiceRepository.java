package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.Invoice;
import com.demchenko.homepay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByUser(User user);
}
