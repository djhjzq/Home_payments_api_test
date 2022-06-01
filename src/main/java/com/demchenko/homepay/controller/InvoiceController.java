package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.InvoiceRegistryForm;
import com.demchenko.homepay.dto.response.InvoiceResponse;
import com.demchenko.homepay.mapper.InvoiceMapper;
import com.demchenko.homepay.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/user/invoices")
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
    public ResponseEntity<InvoiceResponse> addInvoice(@Valid InvoiceRegistryForm invoiceRegistryForm) {
        return new ResponseEntity<>(invoiceMapper.invoiceToInvoiceDto(invoiceService.addInvoice(invoiceRegistryForm)),
                HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<Set<InvoiceResponse>> showInvoices(@Positive Long estateId) {
       return new ResponseEntity<>(invoiceService.findAllEstateInvoices(estateId).stream()
               .map(invoiceMapper::invoiceToInvoiceDto).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteInvoice(@Positive Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
