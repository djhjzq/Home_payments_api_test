package com.demchenko.homepay.service;


import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.Invoice;
import com.demchenko.homepay.entity.User;

import java.util.List;
import java.util.Set;


public interface UserService {

    User findUserById(Long userId);

    User findUserByEmailAndPassword(String email, String password);

    void createUser(String firstName, String lastName,
                    String email, String password);
    
    List<User> findAllUsers();

    Set<Invoice> findAllInvoices(Long userId);

    Set<Estate> findAllEstates(Long userId);
}
