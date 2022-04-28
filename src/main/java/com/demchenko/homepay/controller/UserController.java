package com.demchenko.homepay.controller;

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

    @PostMapping
    public User findUserByEmailAndPassword(@RequestParam String email,
                                           @RequestParam String password) {
        return userService.findUserByEmailAndPassword(email, password);
    }


}
