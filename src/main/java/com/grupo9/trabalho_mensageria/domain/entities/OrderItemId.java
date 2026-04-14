package com.grupo9.trabalho_mensageria.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class OrderItemId implements Serializable {
    private Integer id;
    private UUID order;
}