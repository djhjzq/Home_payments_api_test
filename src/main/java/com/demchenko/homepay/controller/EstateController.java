package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.EstateRegistryForm;
import com.demchenko.homepay.dto.response.EstateDto;
import com.demchenko.homepay.mapper.EstateMapper;
import com.demchenko.homepay.service.CityService;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/objects")
public class EstateController {

    private final EstateService estateService;

    private final CityService cityService;

    private final StreetService streetService;

    private final EstateMapper estateMapper;

    @Autowired
    public EstateController(EstateService estateService, CityService cityService, StreetService streetService, EstateMapper estateMapper) {
        this.estateService = estateService;
        this.cityService = cityService;
        this.streetService = streetService;
        this.estateMapper = estateMapper;
    }

    @PostMapping("/all")
    public Set<EstateDto> showObjects(Long userId) {
        return estateService.findAllEstatesByUserId(userId).stream()
                .map(estateMapper:: estateToEstateDto).collect(Collectors.toSet());
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

    @DeleteMapping("/delete")
    public void deleteEstate(Long userId, Long cityId, Long streetId, Long estateId) {
        estateService.deleteEstate(userId, cityId, streetId, estateId);
    }

}
