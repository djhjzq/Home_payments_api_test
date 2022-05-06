package com.demchenko.homepay.specification;

import com.demchenko.homepay.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public record UserSpecification(String lastName, String email) implements Specification<User> {

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Set<Predicate> predicates = new HashSet<>();

        if(!StringUtils.isBlank(lastName)) {
            predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
        }

        if(!StringUtils.isBlank(email)) {
            predicates.add(criteriaBuilder.equal(root.get("email"), email));
        }

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }
}
