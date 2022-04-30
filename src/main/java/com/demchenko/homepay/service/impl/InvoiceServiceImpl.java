package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.entity.Invoice;
import com.demchenko.homepay.entity.InvoiceType;
import com.demchenko.homepay.repository.InvoiceRepository;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final EstateService estateService;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, EstateService estateService) {
        this.invoiceRepository = invoiceRepository;
        this.estateService = estateService;
    }

    @Override
    public void createInvoice(String name, String invoiceType, Long estateId) {
        Invoice invoice = new Invoice();
        invoice.setName(name);
        invoice.setEstate(estateService.findEstateById(estateId));
        invoice.setInvoiceType(InvoiceType.valueOf(invoiceType));
        invoiceRepository.save(invoice);
    }

    @Override
    public void addInvoice(InvoiceRegistryForm invoiceRegistryForm) {
        createInvoice(invoiceRegistryForm.getName(),
                invoiceRegistryForm.getInvoiceType(),
                invoiceRegistryForm.getEstateId());
    }
}
