package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Invoice;


public interface InvoiceService {

    void createInvoice(String name, String invoiceType, Long userId);

    Invoice findInvoiceByName(String name);
}
