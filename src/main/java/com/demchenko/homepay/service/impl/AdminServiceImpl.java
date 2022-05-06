package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.service.AdminService;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final EstateService estateService;

    private final UserService userService;

    @Autowired
    public AdminServiceImpl(EstateService estateService, UserService userService) {
        this.estateService = estateService;
        this.userService = userService;
    }

    @Override
    public List<Estate> search(Long cityId, Long streetId, Integer houseNumber, Long estateType) {
       return estateService.search(cityId, streetId, houseNumber, estateType);
    }

    @Override
    public List<Estate> findAllObjects() {
        return estateService.findAllEstates();
    }

    @Override
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public List<User> searchUsers(String lastName, String email) {
        return userService.searchUsersBy(lastName, email);
    }
}
