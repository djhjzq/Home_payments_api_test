package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "estates")
public class Estate extends BaseEntity {

    @Column(name = "house_Number")
    private Integer houseNumber;

    @Column(name = "flat_Number")
    private Integer flatNumber;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "estateSet")
    private Set<User> userSet = new HashSet<>();

    @OneToMany(mappedBy = "estate")
    private Set<Payment> paymentSet;

    @ManyToOne
    private City city;

    @ManyToOne
    private Street street;

    private EstateType estateType;

}
