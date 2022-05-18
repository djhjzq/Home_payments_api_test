package com.demchenko.homepay.service;

import com.demchenko.homepay.dto.request.EstateRegistryForm;
import com.demchenko.homepay.entity.Estate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface EstateService {

    void createEstate(Long userId,
                      Long cityId,
                      Long streetId, Integer houseNumber,
                      Integer flatNumber);

    void registryEstate(EstateRegistryForm estateRegistryForm);

    Estate findEstateById(Long id);

    Set<Estate> findAllEstatesByUserId(Long userId);

    void deleteEstate(Long userId, Long cityId, Long streetId, Long estateId);

    List<Estate> search(Long cityId, Long streetId, Integer houseNumber, Long estateType);

    Page<Estate> findAllEstates(Pageable pageable);


}
