package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class  User extends Person {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Payment> paymentSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_estate",
    joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "estate_id"))
    private Set<Estate> estateSet = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messageList;

    @OneToMany(mappedBy = "userAddress")
    private List<Message> inMessage;

}
