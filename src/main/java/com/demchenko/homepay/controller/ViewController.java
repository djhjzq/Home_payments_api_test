package com.demchenko.homepay.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;



@Controller
public class ViewController {

    @GetMapping()
    public String homePage(@CookieValue(value = "Authorization", defaultValue = "") String cookieAuth,
                           @CookieValue(value = "Role", defaultValue = "") String cookieRole) {
        if (!cookieAuth.equals("") && !cookieRole.equals("")) {
            if (cookieRole.equals("ROLE_USER")) {
                return "redirect:/api/user";
            }
            return "redirect:/api/admin";
        }
        return "index";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/user")
    public String userPage() {
        return "user_page";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/admin")
    public String adminPage() {
        return "admin_page";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/api/logout")
    public String logoutPage(HttpServletResponse response) {
        Cookie cookieAuth = new Cookie("Authorization", "");
        Cookie cookieRole = new Cookie("Role", "");
        cookieAuth.setPath("/");
        cookieRole.setPath("/");
        cookieAuth.setMaxAge(0);
        cookieRole.setMaxAge(0);
        response.addCookie(cookieAuth);
        response.addCookie(cookieRole);
        return "redirect:/";
    }

}
