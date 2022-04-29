package com.demchenko.homepay.controller;

import com.demchenko.homepay.entity.Invoice;
import com.demchenko.homepay.service.InvoiceService;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("user/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final UserService userService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, UserService userService) {
        this.invoiceService = invoiceService;
        this.userService = userService;
    }

    @PostMapping
    public Invoice findInvoiceByName(@RequestParam String invoiceName) {
        return invoiceService.findInvoiceByName(invoiceName);
    }

    @PostMapping("/new")
    public void addInvoice(@RequestParam String name,
                           @RequestParam String invoiceType,
                           @RequestParam Long userId) {
        invoiceService.createInvoice(name, invoiceType, userId);
    }

    @PostMapping("/all")
    public Set<Invoice> findAllUserInvoices(@RequestParam Long userId) {
        return userService.findAllInvoices(userId);
    }

}
