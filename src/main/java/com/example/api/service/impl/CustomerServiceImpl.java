package com.example.api.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.api.domain.Address;
import com.example.api.exception.ValidationException;
import com.example.api.service.CustomerService;
import com.example.api.service.ViaCepService;
import com.example.api.specification.CustomerSpecification;
import com.example.api.validation.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final ViaCepService viaCepService;
	private final CustomerValidator customerValidator;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository,
							   ViaCepService viaCepService,
							   CustomerValidator customerValidator) {
		this.customerRepository = customerRepository;
		this.viaCepService = viaCepService;
		this.customerValidator = customerValidator;
	}

	@Override
	public Page<Customer> findAll(PageRequest pageRequest) {
		return customerRepository.findAll(pageRequest);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	public Optional<Customer> findCustomersByFilter(String name, String email, String gender, String city, String state) {
		Specification<Customer> spec = Specification.where(CustomerSpecification.hasName(name))
				.and(CustomerSpecification.hasEmail(email))
				.and(CustomerSpecification.hasGender(gender))
				.and(CustomerSpecification.hasCity(city))
				.and(CustomerSpecification.hasState(state));

		return customerRepository.findAll(spec);
	}
	@Override
	public Customer createCustomer(Customer customer, String cep) {
		Address address = viaCepService.getAddressByCep(cep);
		customer.setAddresses(Collections.singletonList(address));
		customerValidator.validateCustomer(customer);
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Long id, Customer customerDetails) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ValidationException("Cliente não encontrado"));
		customerValidator.validateCustomer(customerDetails);
		return customerRepository.save(customerDetails);
	}

	@Override
	public boolean deleteCustomer(Long id) {
		return customerRepository.findById(id)
				.map(customer -> {
					customerRepository.delete(customer);
					return true;
				})
				.orElse(false);
	}


}
