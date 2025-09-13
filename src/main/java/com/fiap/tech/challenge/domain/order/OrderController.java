package com.fiap.tech.challenge.domain.order;

import com.fiap.tech.challenge.domain.order.dto.OrderRequestDTO;
import com.fiap.tech.challenge.domain.order.dto.OrderResponseDTO;
import com.fiap.tech.challenge.global.search.filter.Connection;
import com.fiap.tech.challenge.global.search.filter.PageFilterInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    private final OrderServiceGateway service;

    public OrderController(OrderServiceGateway service) {
        this.service = service;
    }

    @QueryMapping
    public Connection<OrderResponseDTO> getOrders(
            @Argument OrderRequestDTO filter,
            @Argument PageFilterInput page
    ) {
        return service.getOrders(filter, page);
    }
}

