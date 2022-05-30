package com.demchenko.homepay.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ViewController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String userPage() {
        return "user_page";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getUser")
    public String getUser() {
        return "redirect:/api/user";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPage() {
        return "admin_page";
    }

}
