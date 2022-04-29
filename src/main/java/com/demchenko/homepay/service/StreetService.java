package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Street;

public interface StreetService {

    void createStreet(Long cityId, String streetName);

    Street findStreetById(Long streetId);
}
