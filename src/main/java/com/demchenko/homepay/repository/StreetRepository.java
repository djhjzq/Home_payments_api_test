package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, Long> {
}
