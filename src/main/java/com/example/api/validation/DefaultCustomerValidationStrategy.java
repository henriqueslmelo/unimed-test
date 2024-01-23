package com.example.api.validation;

import org.apache.commons.validator.routines.EmailValidator;
import com.example.api.domain.Customer;
import com.example.api.domain.Address;
import com.example.api.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class DefaultCustomerValidationStrategy implements CustomerValidationStrategy {

    @Override
    public void validate(Customer customer) {
        validateName(customer.getName());
        validateEmail(customer.getEmail());
        validateAddresses(customer.getAddresses());
        // Adicione aqui outras validações para campos adicionais do Customer
    }

    private void validateName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new ValidationException("Nome não pode ser vazio");
        }
    }

    private void validateEmail(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new ValidationException("Email inválido");
        }
    }

    private void validateAddresses(List<Address> addresses) {
        if (addresses == null || addresses.isEmpty()) {
            throw new ValidationException("Endereço não pode ser nulo ou vazio");
        }

        for (Address address : addresses) {
            validateAddress(address);
        }
    }

    private void validateAddress(Address address) {
        if (!StringUtils.hasText(address.getCity())) {
            throw new ValidationException("Cidade do endereço é obrigatória");
        }
        if (!StringUtils.hasText(address.getState())) {
            throw new ValidationException("Estado do endereço é obrigatório");
        }
        if (!StringUtils.hasText(address.getZipCode()) || address.getZipCode().length() != 5) {
            throw new ValidationException("CEP inválido");
        }
    }
}
