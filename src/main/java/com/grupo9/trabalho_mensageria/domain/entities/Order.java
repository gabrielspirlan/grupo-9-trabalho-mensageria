package com.grupo9.trabalho_mensageria.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    private UUID uuid;

    @Column(name = "created_at")
    @com.fasterxml.jackson.annotation.JsonProperty("created_at")
    private Date createdAt;

    @JsonIgnore
    @Column(name = "indexed_at", updatable = false)
    private Date indexedAt = new Date();

    @Column(name = "processed_at")
    private Date processedAt;

    private String channel;

    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> shipment;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> payment;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = jakarta.persistence.FetchType.EAGER)
    private List<OrderItem> items = new java.util.ArrayList<>();

    private BigDecimal total;
}
