package com.fiap.tech.challenge.domain.order.enumerated;

import org.slf4j.Logger;

import java.util.Date;

public enum OrderStatusEnum {
    REQUESTED("Pedido {} criado em {}."),
    CONFIRMED("Pedido {} confirmado. Iniciando preparo."),
    WAITING_FOR_PICKUP("Pedido {} aguardando retirada."),
    ON_DELIVERY_ROUTE("Pedido {} está em rota de entrega."),
    DELIVERED("Pedido {} foi entregue em {}."),
    CANCELED("Pedido {} foi cancelado.");

    private final String message;

    OrderStatusEnum(String message) {
        this.message = message;
    }

    public void log(String hashId, Date orderDate, Logger log) {
        switch (this) {
            case REQUESTED, DELIVERED -> log.info(message, hashId, orderDate);
            default -> log.info(message, hashId);
        }
    }
}


