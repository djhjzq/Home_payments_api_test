package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.repository.EstateRepository;
import com.demchenko.homepay.service.AdminService;
import com.demchenko.homepay.specification.EstateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final EstateRepository estateRepository;

    @Autowired
    public AdminServiceImpl(EstateRepository estateRepository) {
        this.estateRepository = estateRepository;
    }

    @Override
    public List<Estate> search(Long cityId, Long streetId, Integer houseNumber, Long estateType) {
        EstateSpecification estateSpecification = new EstateSpecification(cityId, streetId, houseNumber, estateType);
        return estateRepository.findAll(estateSpecification);
    }
}
