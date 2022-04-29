package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.repository.EstateRepository;
import com.demchenko.homepay.service.EstateService;
import org.springframework.stereotype.Service;

@Service
public class EstateServiceImpl implements EstateService {

    private final EstateRepository estateRepository;

    public EstateServiceImpl(EstateRepository estateRepository) {
        this.estateRepository = estateRepository;
    }
}
