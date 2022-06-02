package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Message extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private User userAddress;

    String text;

}
