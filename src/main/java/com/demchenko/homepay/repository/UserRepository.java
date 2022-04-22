package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
