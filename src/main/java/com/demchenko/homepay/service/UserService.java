package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.User;

import java.util.List;


public interface UserService {

    void createUser(String firstName, String lastName, String email,
                    String password);

    List<User> findAllUsers();

    User findUserById(Long userId);
}
