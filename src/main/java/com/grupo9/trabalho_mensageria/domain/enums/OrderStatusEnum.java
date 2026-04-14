package com.grupo9.trabalho_mensageria.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    CREATED("created"),
    PAID("paid"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELED("canceled"),
    SEPARATED("separated");

    @com.fasterxml.jackson.annotation.JsonValue
    private String description;

    OrderStatusEnum (String description) {
        this.description = description;
    }

    public static OrderStatusEnum fromDescription(String description) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            if (status.description.equalsIgnoreCase(description)) {
                return status;
            }
        }
        return null;
    }

}
