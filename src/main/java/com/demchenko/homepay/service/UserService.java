package com.demchenko.homepay.service;


import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.response.UserResponse;
import com.demchenko.homepay.entity.User;



public interface UserService {

    User findUserById(Long userId);

    UserResponse findUserByEmailAndPassword(String email, String password);

    void createUser(String firstName, String lastName,
                    String email, String password);

    void registryUser(UserRegistryForm userRegistryForm);

}
