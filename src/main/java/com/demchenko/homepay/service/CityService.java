package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.City;

public interface CityService {

    void createCity(String cityName);

    City findCityById(Long cityId);

}
