package com.grupo9.trabalho_mensageria.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "order_itens")
public class OrderItem {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_uuid")
    @JsonIgnore
    private Order  order;

    @Column(name = "unit_price")
    @JsonProperty("unit_price")
    private Double unitPrice;
    private Double total;

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
