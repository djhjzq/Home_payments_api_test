package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.UserLoginForm;
import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.response.JwtResponse;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid UserLoginForm userLoginForm, HttpServletResponse response) {
        JwtResponse jwtResponse = userService.authenticateUser(userLoginForm);
        userService.addCookies(jwtResponse, response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registry")
    public ResponseEntity<?> registryUser(@Valid UserRegistryForm userRegistryForm) {
        return userService.registryUser(userRegistryForm);
    }
}
