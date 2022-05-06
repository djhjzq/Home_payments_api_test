package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EstateRepository extends JpaRepository<Estate, Long>,
        JpaSpecificationExecutor<Estate> {
}
