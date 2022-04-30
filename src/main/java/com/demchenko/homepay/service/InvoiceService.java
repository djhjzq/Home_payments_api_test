package com.demchenko.homepay.service;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;


public interface InvoiceService {

    void createInvoice(String name, String invoiceType, Long estateId);

    void addInvoice(InvoiceRegistryForm invoiceRegistryForm);

}
