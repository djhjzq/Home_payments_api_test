package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.UserRegistryForm;
import com.demchenko.homepay.dto.response.UserResponse;
import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.Invoice;
import com.demchenko.homepay.entity.Role;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.repository.UserRepository;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


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
    public UserResponse findUserByEmailAndPassword(String email, String password) {
        UserResponse userResponse = new UserResponse();
        User user = userRepository.findUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Incorrect email or password"));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        return userResponse;
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

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Set<Invoice> findAllInvoices(Long userId) {
        return findUserById(userId).getInvoiceSet();
    }

    @Override
    public Set<Estate> findAllEstates(Long userId) {
        return findUserById(userId).getEstateSet();
    }
}
