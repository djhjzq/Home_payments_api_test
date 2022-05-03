package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.dto.request.EstateRegistryForm;
import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.EstateType;
import com.demchenko.homepay.repository.EstateRepository;
import com.demchenko.homepay.service.CityService;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.StreetService;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EstateServiceImpl implements EstateService {

    private final UserService userService;

    private final EstateRepository estateRepository;

    private final StreetService streetService;

    private final CityService cityService;

    @Autowired
    public EstateServiceImpl(UserService userService, EstateRepository estateRepository, StreetService streetService, CityService cityService) {
        this.userService = userService;
        this.estateRepository = estateRepository;
        this.streetService = streetService;
        this.cityService = cityService;
    }

    @Override
    public void createEstate(Long userId,
                             Long cityId,
                             Long streetId, Integer houseNumber,
                             Integer flatNumber) {
        Estate estate = new Estate();
        estate.setStreet(streetService.findStreetById(streetId));
        estate.setCity(cityService.findCityById(cityId));
        estate.setHouseNumber(houseNumber);
        estate.setFlatNumber(flatNumber);

        if(flatNumber != 0) {
            estate.setEstateType(EstateType.FLAT);
        } else estate.setEstateType(EstateType.HOUSE);

        userService.findUserById(userId).getEstateSet().add(estate);

        estateRepository.save(estate);
    }

    @Override
    public void registryEstate(EstateRegistryForm estateRegistryForm) {
        createEstate(estateRegistryForm.userId(), estateRegistryForm.cityId(),
                estateRegistryForm.streetId(), estateRegistryForm.houseNumber(),
                estateRegistryForm.flatNumber());
    }

    @Override
    public Estate findEstateById(Long id) {
        return estateRepository.findById(id).
                orElseThrow(()-> new RuntimeException("estate with this id not found"));
    }

    @Override
    public Set<Estate> findAllEstatesByUserId(Long userId) {
        return userService.findUserById(userId).getEstateSet();
    }
}
