package com.example.api.service.impl;

import com.example.api.domain.Address;
import com.example.api.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepServiceImpl implements ViaCepService {

    private final RestTemplate restTemplate;

    public ViaCepServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Address getAddressByCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, Address.class);
    }
}
