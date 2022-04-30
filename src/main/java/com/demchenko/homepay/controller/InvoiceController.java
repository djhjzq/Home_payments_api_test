package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("user/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;


    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/new")
    public void addInvoice(InvoiceRegistryForm invoiceRegistryForm) {
        invoiceService.addInvoice(invoiceRegistryForm);
    }

}
