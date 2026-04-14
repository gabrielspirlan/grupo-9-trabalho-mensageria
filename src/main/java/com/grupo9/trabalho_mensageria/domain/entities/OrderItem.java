package com.grupo9.trabalho_mensageria.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "order_itens")
@IdClass(OrderItemId.class)
public class OrderItem {

    @Id
    @JsonIgnore
    private Integer id;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_uuid")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    private Integer quantity;

    @Column(name = "unit_price")
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    private BigDecimal total;

    @JsonProperty("product_id")
    public Integer getProductId() {
        return product != null ? product.getId() : null;
    }

    @JsonProperty("product_name")
    public String getProductName() {
        return product != null ? product.getName() : null;
    }

    @JsonProperty("category")
    public Object getCategory() {
        return product != null ? product.getCategory() : null;
    }
}
