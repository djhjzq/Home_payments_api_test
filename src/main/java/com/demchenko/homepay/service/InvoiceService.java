package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    void createInvoice(String name, String invoiceType, Long userId);

    List<Invoice> findAllUserInvoices(Long userId);
}
