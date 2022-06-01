package com.demchenko.homepay.service;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.entity.Invoice;

import java.util.Set;


public interface InvoiceService {

    Invoice createInvoice(String name, String invoiceType, Long estateId);

    Invoice addInvoice(InvoiceRegistryForm invoiceRegistryForm);

    Invoice findInvoiceById(Long invoiceId);

    Set<Invoice> findAllEstateInvoices(Long estateId);

    void deleteInvoice(Long invoiceId);

}
