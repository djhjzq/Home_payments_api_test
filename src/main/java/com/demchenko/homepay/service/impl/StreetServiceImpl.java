package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.Street;
import com.demchenko.homepay.exception.StreetNotFoundException;
import com.demchenko.homepay.repository.StreetRepository;
import com.demchenko.homepay.service.StreetService;
import com.demchenko.homepay.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreetServiceImpl implements StreetService {

    private final StreetRepository streetRepository;

    private final CityService cityService;

    @Autowired
    public StreetServiceImpl(StreetRepository streetRepository, CityService cityService) {
        this.streetRepository = streetRepository;
        this.cityService = cityService;
    }

    @Override
    public void createStreet(Long cityId, String streetName) {
        Street street = new Street();
        street.setName(streetName);
        street.setCity(cityService.findCityById(cityId));
        streetRepository.save(street);
    }

    @Override
    public Street findStreetById(Long streetId) {
        return streetRepository.findById(streetId)
                .orElseThrow(()-> new StreetNotFoundException
                        ("Street by id " + streetId + " was not found."));
    }
}
