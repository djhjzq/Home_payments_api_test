package com.demchenko.homepay.service;


import com.demchenko.homepay.entity.User;


public interface UserService {

    User findUserByEmail(String email);

}
