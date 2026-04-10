package com.grupo9.trabalho_mensageria.domain.converters;

import com.grupo9.trabalho_mensageria.domain.enums.OrderStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatusEnum status) {
        if (status == null) {
            return null;
        }
        return status.getDescription();
    }

    @Override
    public OrderStatusEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return OrderStatusEnum.fromDescription(dbData);
    }
}
