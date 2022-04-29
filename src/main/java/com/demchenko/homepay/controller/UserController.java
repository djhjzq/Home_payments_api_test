package com.demchenko.homepay.controller;

import com.demchenko.homepay.entity.Invoice;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.service.InvoiceService;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final InvoiceService invoiceService;

    @Autowired
    public UserController(UserService userService, InvoiceService invoiceService) {
        this.userService = userService;
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public User findUserByEmailAndPassword(@RequestParam String email,
                                           @RequestParam String password) {
        return userService.findUserByEmailAndPassword(email, password);
    }

    @PostMapping("/new")
    public void createUser(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String password) {
        userService.createUser(firstName, lastName, email, password);
    }

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }


    @PostMapping("/invoice/new")
    public void addInvoice(@RequestParam String name,
                           @RequestParam String invoiceType,
                           @RequestParam Long userId) {
        invoiceService.createInvoice(name, invoiceType, userId);
    }

    @PostMapping("/invoice/all")
    public Set<Invoice> findAllInvoices(Long userId) {
        return userService.findAllInvoices(userId);
    }

    @PostMapping("/invoice")
    public Invoice findInvoiceByName(@RequestParam String invoiceName) {
        return invoiceService.findInvoiceByName(invoiceName);
    }

}
