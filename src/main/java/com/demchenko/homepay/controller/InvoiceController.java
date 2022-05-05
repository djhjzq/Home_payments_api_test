package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.dto.response.InvoiceDto;
import com.demchenko.homepay.mapper.InvoiceMapper;
import com.demchenko.homepay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceMapper invoiceMapper) {
        this.invoiceService = invoiceService;
        this.invoiceMapper = invoiceMapper;
    }

    @PostMapping("/new")
    public void addInvoice(InvoiceRegistryForm invoiceRegistryForm) {
        invoiceService.addInvoice(invoiceRegistryForm);
    }

    @PostMapping("/all")
    public Set<InvoiceDto> showInvoices(Long estateId) {

       return invoiceService.findAllEstateInvoices(estateId).stream()
               .map(invoiceMapper::invoiceToInvoiceDto).collect(Collectors.toSet());
    }

    @DeleteMapping("/delete")
    public void deleteInvoice(Long estateId, Long invoiceId) {
        invoiceService.deleteInvoice(estateId, invoiceId);
    }

}
