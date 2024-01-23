package com.example.api.service;

import com.example.api.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService {


    Page<Customer> findAll(PageRequest pageRequest);

    Optional<Customer> findById(Long id);

    Customer createCustomer(Customer customer, String cep);

    Customer updateCustomer(Long id, Customer customerDetails);

    boolean deleteCustomer(Long id);

    Optional<Customer> findCustomersByFilter(String s, String s1, String s2, String s3, String s4);
}
