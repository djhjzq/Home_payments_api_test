package com.demchenko.homepay.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;



@Controller
@RequestMapping("/api")
public class ViewController {


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(HttpServletResponse response) {
        return "user_page";
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPage() {
        return "admin_page";
    }

}
