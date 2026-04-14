package com.grupo9.trabalho_mensageria.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "customers")
public class Customer {

    @Id
    private Integer id;
    private String name;
    private String email;
    private String document;
}
