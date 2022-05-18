package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    List<Estate> search(Long cityId, Long streetId, Integer houseNumber,
                        Long estateType);

    Page<Estate> findAllObjects(Pageable pageable);

    Page<User> findAllUsers(Pageable pageable);

    List<User> searchUsers(String lastName, String email);

}
