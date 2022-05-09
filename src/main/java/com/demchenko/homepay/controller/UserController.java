package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.request.UserUpdateForm;
import com.demchenko.homepay.dto.response.UserDto;
import com.demchenko.homepay.mapper.UserMapper;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


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
    public UserDto findUserByEmailAndPassword(@NotBlank String email,
                                                   @NotBlank String password) {
        return userMapper.userToUserDto(userService.
                findUserByEmailAndPassword(email, password));
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
