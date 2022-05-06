package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.User;

import java.util.List;

public interface AdminService {

    List<Estate> search(Long cityId, Long streetId, Integer houseNumber,
                        Long estateType);

    List<Estate> findAllObjects();

    List<User> findAllUsers();

    List<User> searchUsers(String lastName, String email);

}
