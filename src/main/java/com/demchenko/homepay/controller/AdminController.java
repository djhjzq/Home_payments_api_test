package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.response.EstateDto;
import com.demchenko.homepay.mapper.EstateMapper;
import com.demchenko.homepay.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final EstateMapper estateMapper;

    @Autowired
    public AdminController(AdminService adminService, EstateMapper estateMapper) {
        this.adminService = adminService;
        this.estateMapper = estateMapper;
    }

    @PostMapping("/search")
    public List<EstateDto> searchObjectsBy(@RequestParam(required = false) Long cityId,
                                            @RequestParam(required = false) Long streetId,
                                            @RequestParam(required = false) Integer houseNumber,
                                            @RequestParam(required = false) Long estateType) {
        return adminService.search(cityId, streetId, houseNumber, estateType).stream()
                .map(estateMapper :: estateToEstateDto).collect(Collectors.toList());
    }

    @GetMapping("/objects")
    public List<EstateDto> findAllObjects() {
        return adminService.findAllObjects().stream()
                .map(estateMapper :: estateToEstateDto).collect(Collectors.toList());
    }

}
