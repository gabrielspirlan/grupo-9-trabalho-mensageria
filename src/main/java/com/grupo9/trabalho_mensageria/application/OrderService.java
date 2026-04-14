package com.grupo9.trabalho_mensageria.application;

import com.grupo9.trabalho_mensageria.domain.entities.Order;
import com.grupo9.trabalho_mensageria.infrastructure.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> findByUuid(UUID uuid) {
        return orderRepository.findById(uuid);
    }

    public Page<Order> findAll(Integer codigoCliente, Integer codigoProduto, String status, Pageable pageable) {
        return orderRepository.findWithFilters(codigoCliente, codigoProduto, status, pageable);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }


}
