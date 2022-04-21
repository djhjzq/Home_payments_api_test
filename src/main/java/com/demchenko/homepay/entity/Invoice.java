package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice extends NamedEntity {

    @ManyToOne
    private InvoiceType invoiceType;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    private Set<Payment> paymentSet;

}