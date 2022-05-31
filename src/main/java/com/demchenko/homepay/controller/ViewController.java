package com.demchenko.homepay.controller;


import com.demchenko.homepay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;



@Slf4j
@Controller
public class ViewController {

    private final UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String homePage(@CookieValue(value = "Authorization", defaultValue = "") String cookieAuth,
                           @CookieValue(value = "Role", defaultValue = "") String cookieRole) {
        if (!cookieAuth.equals("") && !cookieRole.equals("")) {
            if (cookieRole.equals("ROLE_USER")) {
                log.info("redirect:/api/user");
                return "redirect:/api/user";
            }
            log.info("redirect:/api/admin");
            return "redirect:/api/admin";
        }
        return "index";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/user")
    public String userPage(Model model) {
        model.addAttribute("m", "a");
        log.info("return user_page");
        return "user_page";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/admin")
    public String adminPage() {
        log.info("return admin_page");
        return "admin_page";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/api/logout")
    public String logoutPage(HttpServletResponse response) {
        userService.refreshCookie(response);
        log.info("Cookie is deleted. Redirect:/");
        return "redirect:/";
    }

}
