package com.grupo9.trabalho_mensageria.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product {
    @Id
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
