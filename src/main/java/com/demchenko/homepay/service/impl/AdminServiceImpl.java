package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.service.AdminService;
import com.demchenko.homepay.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final EstateService estateService;

    @Autowired
    public AdminServiceImpl(EstateService estateService) {
        this.estateService = estateService;
    }

    @Override
    public List<Estate> search(Long cityId, Long streetId, Integer houseNumber, Long estateType) {
       return estateService.search(cityId, streetId, houseNumber, estateType);
    }

    @Override
    public List<Estate> findAllObjects() {
        return estateService.findAllEstates();
    }
}
