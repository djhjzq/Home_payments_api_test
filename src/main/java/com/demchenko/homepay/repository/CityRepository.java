package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

}
