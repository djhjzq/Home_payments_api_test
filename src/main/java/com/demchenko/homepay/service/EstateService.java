package com.demchenko.homepay.service;

import com.demchenko.homepay.dto.request.EstateRegistryForm;
import com.demchenko.homepay.entity.Estate;

import java.util.Set;

public interface EstateService {

    void createEstate(Long userId,
                      Long cityId,
                      Long streetId, Integer houseNumber,
                      Integer flatNumber);

    void registryEstate(EstateRegistryForm estateRegistryForm);

    Estate findEstateById(Long id);

    Set<Estate> findAllEstatesByUserId(Long userId);
}
