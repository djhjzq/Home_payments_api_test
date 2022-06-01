package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Street;

public interface StreetService {

    Street createStreet(Long cityId, String streetName);

    Street findStreetById(Long streetId);

    Street findStreetByName(String streetName);

    Street findStreetByCityAndName(Long cityId, String streetName);

}
