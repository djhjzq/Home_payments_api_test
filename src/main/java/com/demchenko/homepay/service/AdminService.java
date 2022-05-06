package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Estate;

import java.util.List;

public interface AdminService {

    List<Estate> search(Long cityId, Long streetId, Integer houseNumber,
                        Long estateType);

}
