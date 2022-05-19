package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.UserLoginForm;
import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.response.JwtResponse;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid UserLoginForm userLoginForm) {
        return new ResponseEntity<>(userService.authenticateUser(userLoginForm),
                HttpStatus.OK);
    }

    @PostMapping("/registry")
    public ResponseEntity<?> registryUser(@Valid UserRegistryForm userRegistryForm) {
        return userService.registryUser(userRegistryForm);
    }
}
