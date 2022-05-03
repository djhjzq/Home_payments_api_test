package com.demchenko.homepay.service;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.entity.Invoice;

import java.util.Set;


public interface InvoiceService {

    void createInvoice(String name, String invoiceType, Long estateId);

    void addInvoice(InvoiceRegistryForm invoiceRegistryForm);

    Invoice findInvoiceByEstateId(Long estateId);

    Set<Invoice> findAllEstateInvoices(Long estateId);

}
