package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.City;
import com.demchenko.homepay.exception.CityNotFoundException;
import com.demchenko.homepay.repository.CityRepository;
import com.demchenko.homepay.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City createCity(String cityName) {
        City city = new City();
        city.setName(cityName);
        return cityRepository.save(city);
    }

    @Override
    public City findCityById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException
                        ("City by id " + cityId + " was not found."));
    }
}
