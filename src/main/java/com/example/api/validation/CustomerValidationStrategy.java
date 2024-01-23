package com.example.api.validation;

import com.example.api.domain.Customer;

public interface CustomerValidationStrategy {
    void validate(Customer customer);
}