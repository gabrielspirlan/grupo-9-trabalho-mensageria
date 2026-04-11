package com.grupo9.trabalho_mensageria.infrastructure.repositories;

import com.grupo9.trabalho_mensageria.domain.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {


    @Query(
            "SELECT DISTINCT o FROM orders o " +
            "LEFT JOIN FETCH o.items i " +
            "WHERE (:clientId IS NULL OR o.customer.id = :clientId) " +
            "AND (:status IS NULL OR o.status = :status) " +
            "AND (:productId IS NULL OR i.product.id = :productId) " +
            "ORDER BY o.createdAt DESC"
    )
    Page<Order> findWithFilters(
        @Param("clientId") Integer clientId,
        @Param("productId") Integer productId,
        @Param("status") String status,
        Pageable pageable
    );
}
