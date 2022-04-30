package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.entity.Invoice;
import com.demchenko.homepay.entity.InvoiceType;
import com.demchenko.homepay.repository.InvoiceRepository;
import com.demchenko.homepay.service.InvoiceService;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final UserService userService;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, UserService userService) {
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
    }

    @Override
    public void createInvoice(String name, String invoiceType, Long userId) {
        Invoice invoice = new Invoice();
        invoice.setName(name);
        invoice.setInvoiceType(InvoiceType.valueOf(invoiceType));
        invoice.setUser(userService.findUserById(userId));
        invoiceRepository.save(invoice);
    }

    @Override
    public void addInvoice(InvoiceRegistryForm invoiceRegistryForm) {
        createInvoice(invoiceRegistryForm.getName(),
                invoiceRegistryForm.getInvoiceType(),
                invoiceRegistryForm.getUserId());
    }

    @Override
    public Invoice findInvoiceByName(String name) {
        return invoiceRepository.findInvoiceByName(name);
    }
}
