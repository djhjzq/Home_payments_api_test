package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Set<User> userSets;

    @ManyToOne
    private City city;

    @ManyToOne
    private Street street;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estate")
    private Set<Invoice> invoiceSet;

    private EstateType estateType;

}
