package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Estate;

public interface EstateService {

    void createEstate(Long userId,
                      Long cityId,
                      Long streetId, Integer houseNumber,
                      Integer flatNumber);

    Estate findEstateById(Long id);
}
