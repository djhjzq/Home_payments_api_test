package com.demchenko.homepay.service;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.entity.Invoice;


public interface InvoiceService {

    void createInvoice(String name, String invoiceType, Long userId);

    void addInvoice(InvoiceRegistryForm invoiceRegistryForm);

    Invoice findInvoiceByName(String name);
}
