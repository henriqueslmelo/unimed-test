package com.example.api.web.rest;


import com.example.api.domain.Address;
import com.example.api.service.impl.ViaCepServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ViaCepServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ViaCepServiceImpl viaCepService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void returnAddress() {
        String cep = "12345678";
        Address mockAddress = new Address(); // Configurar o endereço conforme sua classe Address
        mockAddress.setCity("Test City");

        when(restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json/", Address.class)).thenReturn(mockAddress);

        Address result = viaCepService.getAddressByCep(cep);

        assertEquals("Test City", result.getCity());
        verify(restTemplate).getForObject("https://viacep.com.br/ws/" + cep + "/json/", Address.class);
    }
}

