package com.example.api.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import com.example.api.domain.Customer;
import com.example.api.service.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerServiceImpl customerService;

	@Autowired
	public CustomerController(CustomerServiceImpl customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public Page<Customer> findAllCustomers(@RequestParam(defaultValue = "0") int page,
										   @RequestParam(defaultValue = "10") int size) {
    		return customerService.findAll(PageRequest.of(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
    		return customerService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/filter")
	public Optional<Customer> findCustomersByFilter(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String gender,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String state) {

		Optional<Customer> customers = customerService.findCustomersByFilter(name, email, gender, city, state);
		return ResponseEntity.ok(customers).getBody();

	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer, @RequestParam String cep) {
        	Customer createdCustomer = customerService.createCustomer(customer, cep);
        	URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                              .path("/{id}")
                                              .buildAndExpand(createdCustomer.getId())
                                              .toUri();
    		return ResponseEntity.created(location).body(createdCustomer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
    		Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
    		if (updatedCustomer == null) {
        		return ResponseEntity.notFound().build();
    		}
    		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    		boolean isDeleted = customerService.deleteCustomer(id);
    		if (!isDeleted) {
        		return ResponseEntity.notFound().build();
    		}
    		return ResponseEntity.noContent().build();
	}

}
