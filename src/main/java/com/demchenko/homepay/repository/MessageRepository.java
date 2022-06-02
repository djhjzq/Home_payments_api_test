package com.demchenko.homepay.repository;

import com.demchenko.homepay.entity.Message;
import com.demchenko.homepay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByUserAndUserAddress(User user1, User user2);
}
