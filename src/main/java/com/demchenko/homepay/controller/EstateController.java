package com.demchenko.homepay.controller;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.service.CityService;
import com.demchenko.homepay.service.EstateService;
import com.demchenko.homepay.service.StreetService;
import com.demchenko.homepay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user/objects")
public class EstateController {

    private final EstateService estateService;

    private final UserService userService;

    private final CityService cityService;

    private final StreetService streetService;

    @Autowired
    public EstateController(EstateService estateService, UserService userService, CityService cityService, StreetService streetService) {
        this.estateService = estateService;
        this.userService = userService;
        this.cityService = cityService;
        this.streetService = streetService;
    }

    @PostMapping("/new")
    public void addEstate(@RequestParam Long userId,
                          @RequestParam Long streetId,
                          @RequestParam Integer houseNumber,
                          @RequestParam Integer flatNumber) {
        estateService.createEstate(userId, streetId, houseNumber, flatNumber);
    }

    @PostMapping("/new/city")
    public void createCity(@RequestParam String cityName) {
        cityService.createCity(cityName);
    }

    @PostMapping("/new/street")
    public void createStreet(@RequestParam Long cityId, String streetName) {
        streetService.createStreet(cityId, streetName);
    }

    @PostMapping
    public Set<Estate> findAllEstates(@RequestParam Long userId) {
        return userService.findAllEstates(userId);
    }

}
