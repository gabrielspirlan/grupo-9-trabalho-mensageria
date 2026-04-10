package com.grupo9.trabalho_mensageria.application;

import com.grupo9.trabalho_mensageria.domain.entities.OrderItem;
import com.grupo9.trabalho_mensageria.infrastructure.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem save (OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
