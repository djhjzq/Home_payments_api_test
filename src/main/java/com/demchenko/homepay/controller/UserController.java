package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.request.UserUpdateForm;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;


@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public void registry(@Valid UserRegistryForm userRegistryForm) {
         userService.registryUser(userRegistryForm);
    }

    @PatchMapping("/edit")
    public void editUser(@Valid UserUpdateForm userUpdateForm) {
        userService.updateUser(userUpdateForm);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@Positive Long userId) {
        userService.deleteUser(userId);
    }

}
