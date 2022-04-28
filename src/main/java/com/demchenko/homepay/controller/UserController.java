package com.demchenko.homepay.controller;

import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public void createNewUser(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String email,
                              @RequestParam String password) {

        userService.createUser(firstName, lastName,
                email, password);
    }

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public User findUserByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }


}
