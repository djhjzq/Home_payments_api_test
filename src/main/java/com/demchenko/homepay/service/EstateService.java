package com.demchenko.homepay.service;

public interface EstateService {

    void createEstate(Long userId, Long cityId,
                      Long streetId, Integer houseNumber,
                      Integer flatNumber);
}
