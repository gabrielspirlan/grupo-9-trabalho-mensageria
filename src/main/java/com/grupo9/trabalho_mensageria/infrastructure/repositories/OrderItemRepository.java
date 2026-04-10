package com.grupo9.trabalho_mensageria.infrastructure.repositories;

import com.grupo9.trabalho_mensageria.domain.entities.OrderItem;
import com.grupo9.trabalho_mensageria.domain.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}
