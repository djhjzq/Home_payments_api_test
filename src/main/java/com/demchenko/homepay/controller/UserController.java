package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.response.UserDto;
import com.demchenko.homepay.mapper.UserMapper;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserDto findUserByEmailAndPassword(@RequestParam String email,
                                                   @RequestParam String password) {
        return userMapper.userToUserDto(userService.
                findUserByEmailAndPassword(email, password));
    }

    @PostMapping("/new")
    public void registry(UserRegistryForm userRegistryForm) {
         userService.registryUser(userRegistryForm);
    }

}
