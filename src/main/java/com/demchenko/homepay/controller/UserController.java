package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User findUserByEmailAndPassword(@RequestParam String email,
                                           @RequestParam String password) {
        return userService.findUserByEmailAndPassword(email, password);
    }

    @PostMapping("/new")
    public void registry(UserRegistryForm userRegistryForm) {
         userService.registryUser(userRegistryForm);
    }

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

}
