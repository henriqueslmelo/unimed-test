package com.example.api.repository;

import java.util.List;
import java.util.Optional;

import com.example.api.domain.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findAll(Specification<Customer> spec);
}
