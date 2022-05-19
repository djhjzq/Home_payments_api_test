package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.request.EstateRegistryForm;
import com.demchenko.homepay.dto.response.EstateDto;
import com.demchenko.homepay.mapper.EstateMapper;
import com.demchenko.homepay.service.CityService;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user/objects")
@PreAuthorize("hasRole('ROLE_USER')")
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
    public Set<EstateDto> showObjects(@Positive Long userId) {
        return estateService.findAllEstatesByUserId(userId).stream()
                .map(estateMapper:: estateToEstateDto).collect(Collectors.toSet());
    }

    @PostMapping("/new")
    public void registryEstate(@Valid EstateRegistryForm estateRegistryForm) {
        estateService.registryEstate(estateRegistryForm);
    }

    @PostMapping("/new/city")
    public void createCity(@Size(min = 2, max = 20) String cityName) {
        cityService.createCity(cityName);
    }

    @PostMapping("/new/street")
    public void createStreet(@Positive Long cityId, @Size(min = 2, max = 20) String streetName) {
        streetService.createStreet(cityId, streetName);
    }

    @DeleteMapping("/delete")
    public void deleteEstate(@Positive Long userId, @Positive Long cityId,
                             @Positive Long streetId, @Positive Long estateId) {
        estateService.deleteEstate(userId, cityId, streetId, estateId);
    }

}
