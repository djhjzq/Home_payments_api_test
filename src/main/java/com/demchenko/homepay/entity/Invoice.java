package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.NamedEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice extends NamedEntity {

    private InvoiceType invoiceType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    private Set<Payment> paymentSet;

}
