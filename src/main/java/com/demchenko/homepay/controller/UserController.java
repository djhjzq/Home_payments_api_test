package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.request.UserUpdateForm;
import com.demchenko.homepay.dto.response.UserResponse;
import com.demchenko.homepay.mapper.UserMapper;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;


@RestController
@RequestMapping("api/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<UserResponse> registry(@Valid UserRegistryForm userRegistryForm) {
         return userService.registryUser(userRegistryForm);
    }

    @PatchMapping("/edit")
    public ResponseEntity<UserResponse> editUser(@Valid UserUpdateForm userUpdateForm) {
        return new ResponseEntity<>(userMapper.userToUserDto(userService.updateUser(userUpdateForm)),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@Positive Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
