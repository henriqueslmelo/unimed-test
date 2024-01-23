package com.example.api.validation;

import org.apache.commons.validator.routines.EmailValidator;

import com.example.api.domain.Customer;
import org.springframework.stereotype.Component;


@Component
public class CustomerValidator {

    private CustomerValidationStrategy validationStrategy;

    public CustomerValidator(CustomerValidationStrategy strategy) {
        this.validationStrategy = strategy;
    }

    public void validateCustomer(Customer customer) {
        validationStrategy.validate(customer);
    }
}
