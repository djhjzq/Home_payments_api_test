package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cities")
public class City extends NamedEntity {

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Street> streetSet;

}
