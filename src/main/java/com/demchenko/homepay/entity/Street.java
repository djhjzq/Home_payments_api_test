package com.demchenko.homepay.entity;

import com.demchenko.homepay.entity.basic.NamedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "streets")
public class Street extends NamedEntity {

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "street", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Estate> estateSet;

    public Street(String name) {
        super(name);
    }
}
