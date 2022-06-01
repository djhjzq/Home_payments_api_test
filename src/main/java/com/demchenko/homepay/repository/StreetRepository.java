package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.City;
import com.demchenko.homepay.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, Long> {
    Street findStreetByName(String streetName);
}
