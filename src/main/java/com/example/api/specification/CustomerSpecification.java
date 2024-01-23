package com.example.api.specification;

import com.example.api.domain.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
    public static Specification<Customer> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

public static Specification<Customer> hasEmail(String email) {
    return (root, query, criteriaBuilder) -> {
        if (email == null || email.isEmpty()) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.like(root.get("email"), "%" + email + "%");
    };
}

    public static Specification<Customer> hasGender(String gender) {
        return (root, query, criteriaBuilder) -> {
            if (gender == null || gender.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("gender"), gender);
        };
    }

    public static Specification<Customer> hasCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city == null || city.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("city"), "%" + city + "%");
        };
    }

    public static Specification<Customer> hasState(String state) {
        return (root, query, criteriaBuilder) -> {
            if (state == null || state.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("state"), "%" + state + "%");
        };
    }
}
