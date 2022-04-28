package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.Role;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.repository.UserRepository;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Incorrect email or password"));
    }

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBalance(BigDecimal.valueOf(0));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
