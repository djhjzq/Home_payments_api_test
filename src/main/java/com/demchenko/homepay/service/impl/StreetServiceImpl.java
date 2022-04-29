package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.repository.StreetRepository;
import com.demchenko.homepay.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreetServiceImpl implements StreetService {

    private final StreetRepository streetRepository;

    @Autowired
    public StreetServiceImpl(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }
}
