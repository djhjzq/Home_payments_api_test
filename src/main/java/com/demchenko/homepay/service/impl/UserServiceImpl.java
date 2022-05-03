package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.response.UserDto;
import com.demchenko.homepay.entity.Role;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.mapper.UserMapper;
import com.demchenko.homepay.repository.UserRepository;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user with this id not found"));
    }

    @Override
    public UserDto findUserByEmailAndPassword(String email, String password) {
        return userMapper.userToUserDto(userRepository.findUserByEmailAndPassword(email, password).
                orElseThrow(()-> new RuntimeException("USER NOT FOUND")));
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
    public void registryUser(UserRegistryForm userRegistryForm) {
        createUser(userRegistryForm.getFirstName(), userRegistryForm.getLastName(),
                userRegistryForm.getEmail(), userRegistryForm.getPassword());
    }
}
