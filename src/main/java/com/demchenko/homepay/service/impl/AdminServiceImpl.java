package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.User;
import com.demchenko.homepay.service.AdminService;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.info("Try to find estates by cityId: {}, streetId: {}, houseNumber: {}," +
                "estateType: {}", cityId, streetId, houseNumber, estateType);
       return estateService.search(cityId, streetId, houseNumber, estateType);
    }

    @Override
    public Page<Estate> findAllObjects(Pageable pageable) {
        return estateService.findAllEstates(pageable);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userService.findAllUsers(pageable);
    }

    @Override
    public List<User> searchUsers(String lastName, String email) {
        log.info("Try to find users by lastName: {}, email: {}", lastName, email);
        return userService.searchUsersBy(lastName, email);
    }
}
