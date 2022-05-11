package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.dto.response.InvoiceDto;
import com.demchenko.homepay.mapper.InvoiceMapper;
import com.demchenko.homepay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user/invoices")
@PreAuthorize("hasRole('ROLE_USER')")
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceMapper invoiceMapper) {
        this.invoiceService = invoiceService;
        this.invoiceMapper = invoiceMapper;
    }

    @PostMapping("/new")
    public void addInvoice(@Valid InvoiceRegistryForm invoiceRegistryForm) {
        invoiceService.addInvoice(invoiceRegistryForm);
    }

    @PostMapping("/all")
    public Set<InvoiceDto> showInvoices(@Positive Long estateId) {

       return invoiceService.findAllEstateInvoices(estateId).stream()
               .map(invoiceMapper::invoiceToInvoiceDto).collect(Collectors.toSet());
    }

    @DeleteMapping("/delete")
    public void deleteInvoice(@Positive Long estateId, @Positive Long invoiceId) {
        invoiceService.deleteInvoice(estateId, invoiceId);
    }

}
