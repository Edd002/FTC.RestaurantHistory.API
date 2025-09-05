package com.fiap.tech.challenge.domain.order;

import com.fiap.tech.challenge.domain.order.dto.OrderFilterInput;
import com.fiap.tech.challenge.domain.order.dto.OrderResponseDTO;
import com.fiap.tech.challenge.domain.order.entity.Order;
import com.fiap.tech.challenge.domain.order.specification.OrderSpecificationBuilder;
import com.fiap.tech.challenge.global.search.filter.Connection;
import com.fiap.tech.challenge.global.search.filter.PageFilterInput;
import com.fiap.tech.challenge.global.search.pagination.GraphQLConnectionBuilder;
import com.fiap.tech.challenge.global.search.pagination.GraphQLPageableBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceGateway {

    private final IOrderRepository orderRepository;
    private final ModelMapper modelMapperPresenter;
    private final GraphQLPageableBuilder pageableBuilder;
    private final GraphQLConnectionBuilder connectionBuilder;

    @Autowired
    public OrderServiceGateway(IOrderRepository orderRepository, ModelMapper modelMapperPresenter,
                               GraphQLPageableBuilder pageableBuilder, GraphQLConnectionBuilder connectionBuilder) {
        this.orderRepository = orderRepository;
        this.modelMapperPresenter = modelMapperPresenter;
        this.pageableBuilder = pageableBuilder;
        this.connectionBuilder = connectionBuilder;
    }

    public Connection<OrderResponseDTO> getOrders(OrderFilterInput request, PageFilterInput pageFilter) {
        Pageable pageable = pageableBuilder.build(pageFilter);
        Page<Order> page = orderRepository.findAll(
                new OrderSpecificationBuilder().build(request).orElse(null),
                pageable
        );

        int offset = pageFilter.getAfter() != null ? Integer.parseInt(pageFilter.getAfter()) : 0;
        int pageSize = pageFilter.getFirst() != null ? pageFilter.getFirst() : 10;

        return connectionBuilder.build(page, pageSize, offset,
                entity -> modelMapperPresenter.map(entity, OrderResponseDTO.class));
    }

}
