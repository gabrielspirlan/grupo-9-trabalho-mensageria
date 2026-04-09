package com.grupo9.trabalho_mensageria.infrastructure.repositories;

import com.grupo9.trabalho_mensageria.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {
}
