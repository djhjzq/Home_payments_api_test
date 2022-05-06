package com.demchenko.homepay.specification;

import com.demchenko.homepay.entity.Estate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public record EstateSpecification(Long cityId, Long streetId, Integer houseNumber, Long estateType) implements Specification<Estate> {

    @Override
    public Predicate toPredicate(Root<Estate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Set<Predicate> predicates = new HashSet<>();

        if (cityId != null) {
            predicates.add(criteriaBuilder.equal(root.get("city"), cityId));
        }

        if (streetId != null) {
            predicates.add(criteriaBuilder.equal(root.get("street"), streetId));
        }

        if(houseNumber != null) {
            predicates.add(criteriaBuilder.equal(root.get("houseNumber"), houseNumber));
        }

        if(estateType != null) {
            predicates.add(criteriaBuilder.equal(root.get("estateType"), estateType));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
