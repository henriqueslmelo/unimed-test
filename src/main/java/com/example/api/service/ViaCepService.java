package com.example.api.service;

import com.example.api.domain.Address;

public interface ViaCepService {

    Address getAddressByCep(String cep);
}
