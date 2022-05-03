package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findInvoiceByEstate(Estate estate);

}
