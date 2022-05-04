package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

}
