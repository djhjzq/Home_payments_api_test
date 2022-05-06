package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.request.UserUpdateForm;
import com.demchenko.homepay.entity.Role;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.repository.UserRepository;
import com.demchenko.homepay.service.UserService;
import com.demchenko.homepay.specification.UserSpecification;
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
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user with this id not found"));
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password).
                orElseThrow(()-> new RuntimeException("USER NOT FOUND"));
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
        createUser(userRegistryForm.firstName(), userRegistryForm.lastName(),
                userRegistryForm.email(), userRegistryForm.password());
    }

    @Override
    public void updateUser(UserUpdateForm userUpdateForm) {
        User user = findUserById(userUpdateForm.userId());
        user.setFirstName(userUpdateForm.firstName());
        user.setLastName(userUpdateForm.lastName());
        user.setEmail(userUpdateForm.email());
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsersBy(String lastName, String email) {
        UserSpecification userSpecification = new UserSpecification(lastName, email);
        return userRepository.findAll(userSpecification);
    }
}
