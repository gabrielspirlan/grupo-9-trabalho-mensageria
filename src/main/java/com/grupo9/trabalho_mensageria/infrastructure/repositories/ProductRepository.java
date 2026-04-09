package com.grupo9.trabalho_mensageria.infrastructure.repositories;

import com.grupo9.trabalho_mensageria.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
