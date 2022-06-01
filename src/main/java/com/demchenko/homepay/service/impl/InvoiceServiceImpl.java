package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.entity.Invoice;
import com.demchenko.homepay.entity.InvoiceType;
import com.demchenko.homepay.exception.InvoiceNotFoundException;
import com.demchenko.homepay.repository.InvoiceRepository;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Slf4j
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
    public Invoice createInvoice(String name, String invoiceType, Long estateId) {
        Invoice invoice = new Invoice();
        invoice.setName(name);
        log.info(invoiceType);
        invoice.setEstate(estateService.findEstateById(estateId));
        invoice.setInvoiceType(InvoiceType.valueOf(invoiceType));
        log.info("Save invoice to repository with name: {}, estateId: {}, " +
                "invoiceType: {}", name, estateId, invoiceType);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice addInvoice(InvoiceRegistryForm invoiceRegistryForm) {
        log.info(invoiceRegistryForm.invoiceType());
        return createInvoice(invoiceRegistryForm.name(),
                invoiceRegistryForm.invoiceType(),
                invoiceRegistryForm.estateId());
    }

    @Override
    public Invoice findInvoiceById(Long invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(()-> new InvoiceNotFoundException
                        ("Invoice by id " + invoiceId + " was not found."));
    }

    @Override
    public Set<Invoice> findAllEstateInvoices(Long estateId) {
        log.info("Try to find invoice by estateId: {}", estateId);
        return estateService.findEstateById(estateId).getInvoiceSet();
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        Invoice invoice = findInvoiceById(invoiceId);
        estateService.findEstateById(invoice.getEstate().getId()).getInvoiceSet()
                .remove(invoice);
        log.info("Delete invoice by invoiceId: {}",
                invoiceId);
        invoiceRepository.deleteById(invoiceId);
    }
}
