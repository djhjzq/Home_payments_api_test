package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.repository.UserRepository;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with this email does not found"));
    }
}
