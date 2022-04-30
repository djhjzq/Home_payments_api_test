package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.EstateRegistryForm;
import com.demchenko.homepay.dto.response.EstateResponse;
import com.demchenko.homepay.service.CityService;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/user/objects")
public class EstateController {

    private final EstateService estateService;

    private final CityService cityService;

    private final StreetService streetService;

    @Autowired
    public EstateController(EstateService estateService, CityService cityService, StreetService streetService) {
        this.estateService = estateService;
        this.cityService = cityService;
        this.streetService = streetService;
    }

    @PostMapping
    public Set<EstateResponse> showAllEstates(Set<Long> estatesId) {
        return null;
    }

    @PostMapping("/new")
    public void registryEstate(EstateRegistryForm estateRegistryForm) {
        estateService.registryEstate(estateRegistryForm);
    }

    @PostMapping("/new/city")
    public void createCity(@RequestParam String cityName) {
        cityService.createCity(cityName);
    }

    @PostMapping("/new/street")
    public void createStreet(@RequestParam Long cityId, String streetName) {
        streetService.createStreet(cityId, streetName);
    }

}
