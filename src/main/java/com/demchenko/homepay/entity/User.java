package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "users")
public class  User extends Person {

    private String email;

    private String password;

    private BigDecimal balance;

    @ManyToOne
    private Role role;


}
