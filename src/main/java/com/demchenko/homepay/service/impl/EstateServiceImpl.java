package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.EstateType;
import com.demchenko.homepay.repository.EstateRepository;
import com.demchenko.homepay.service.CityService;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.StreetService;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstateServiceImpl implements EstateService {

    private final UserService userService;

    private final EstateRepository estateRepository;

    private final CityService cityService;

    private final StreetService streetService;

    @Autowired
    public EstateServiceImpl(UserService userService, EstateRepository estateRepository, CityService cityService, StreetService streetService) {
        this.userService = userService;
        this.estateRepository = estateRepository;
        this.cityService = cityService;
        this.streetService = streetService;
    }

    @Override
    public void createEstate(Long userId, Long cityId,
                             Long streetId, Integer houseNumber,
                             Integer flatNumber) {
        Estate estate = new Estate();
        estate.setCity(cityService.findCityById(cityId));
        estate.setStreet(streetService.findStreetById(streetId));
        estate.getUserSet().add(userService.findUserById(userId));
        estate.setHouseNumber(houseNumber);
        estate.setFlatNumber(flatNumber);
        if(flatNumber != 0) {
            estate.setEstateType(EstateType.FLAT);
        } else estate.setEstateType(EstateType.HOUSE);

        estateRepository.save(estate);
    }
}
