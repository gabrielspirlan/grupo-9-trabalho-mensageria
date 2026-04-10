package com.grupo9.trabalho_mensageria.application;

import com.grupo9.trabalho_mensageria.domain.entities.Product;
import com.grupo9.trabalho_mensageria.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
