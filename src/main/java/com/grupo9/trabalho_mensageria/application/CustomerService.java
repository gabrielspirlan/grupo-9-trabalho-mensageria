package com.grupo9.trabalho_mensageria.application;

import com.grupo9.trabalho_mensageria.domain.entities.Customer;
import com.grupo9.trabalho_mensageria.infrastructure.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
