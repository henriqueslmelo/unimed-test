package com.example.api.web.rest;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;

import com.example.api.service.CustomerService;
import com.example.api.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);

        Address address = new Address();
        address.setStreet("Rua Exemplo");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setZipCode("02271-050");
        address.setCountry("Sao Paulo");

        customer = new Customer();
        customer.setId(1L);
        customer.setName("João da Silva");
        customer.setEmail("joao.silva@example.com");
        customer.setGender("Masculino");
        customer.setAddresses(Collections.singletonList(address));

    }

    @Test
    void testFindAllCustomers() {
        PageRequest pageRequest = PageRequest.of(0, 10); // Página 0, 10 itens por página
        Page<Customer> pageOfCustomers = mock(Page.class);
        when(customerService.findAll(pageRequest)).thenReturn(pageOfCustomers);
        Page<Customer> result = customerController.findAllCustomers(0, 10);
        assertEquals(pageOfCustomers, result);
    }


    @Test
    void testFindCustomerByIdSuccess() {
        when(customerService.findById(anyLong())).thenReturn(Optional.of(customer));
        ResponseEntity<Customer> response = customerController.findCustomerById(1L);
        assertNotNull(response);
        assertEquals(customer, response.getBody());
    }

    @Test
    void testGetCustomerByIdNotFound() {
        when(customerService.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Customer> response = customerController.findCustomerById(1L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateCustomer() {
        String cep = "02271-050";  // Exemplo de CEP
        when(customerService.createCustomer(any(Customer.class), eq(cep))).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.createCustomer(customer, cep);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testUpdateCustomerSuccess() {
        Long customerId = 1L;  // Supondo que o ID do cliente seja 1
        when(customerService.updateCustomer(eq(customerId), any(Customer.class))).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.updateCustomer(customerId, customer);
        assertEquals(200, response.getStatusCodeValue());
    }


    @Test
    void testDeleteCustomerSuccess() {
        Long customerId = 1L;
        when(customerService.deleteCustomer(customerId)).thenReturn(true);
        ResponseEntity<Void> response = customerController.deleteCustomer(customerId);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
