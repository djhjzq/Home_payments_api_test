package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    Optional<User> findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Page<User> findAll(Pageable pageable);
}
