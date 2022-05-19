package com.demchenko.homepay.service;


import com.demchenko.homepay.dto.request.UserLoginForm;
import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.request.UserUpdateForm;
import com.demchenko.homepay.dto.response.JwtResponse;
import com.demchenko.homepay.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {

    User findUserById(Long userId);

    User findUserByEmail(String email);

    void createUser(String firstName, String lastName,
                    String email, String password);

    ResponseEntity<?> registryUser(UserRegistryForm userRegistryForm);

    void updateUser(UserUpdateForm userUpdateForm);

    void deleteUser(Long userId);

    Page<User> findAllUsers(Pageable pageable);

    List<User> searchUsersBy(String lastName, String email);

    JwtResponse authenticateUser(UserLoginForm userLoginForm);

    Boolean existByEmail(String email);

}
